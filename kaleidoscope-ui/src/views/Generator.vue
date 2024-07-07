<template>
  <div class="generator">
    <div class="left">
      <el-card class="box-card">
        <div slot="header" class="box-card-header">
          <span>数据库</span>
        </div>
        <el-table
            :data="schemaData"
            :show-header="false"
            highlight-current-row
            @current-change="handleCurrentChange"
            style="width: 100%"
            class="box-card-table">
          <el-table-column
              prop="name"
              label="库名">
          </el-table-column>
        </el-table>
      </el-card>
    </div>
    <div class="right">
      <div class="condition">
        <el-card class="box-card">
          <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
            <div class="box-card-form">
              <el-form-item label="库表" prop="schemaName" class="form-select-schema">
                <el-select v-model="form.schemaName" placeholder="请选择数据库" @change="changeSchema">
                  <el-option :label="item.name" :value="item.name" v-for="item in schemaData" :key="item.name"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="数据表" prop="tableName">
                <el-select v-model="form.tableName" placeholder="请选择表" @change="changeTable" :disabled="isTableDataEmpty">
                  <el-option :label="item" :value="item" v-for="item in tableData" :key="item"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="模板" prop="templateTypes">
                <el-select v-model="form.templateTypes" placeholder="请选择生成模板" :multiple="true" :disabled="formDisabled">
                  <el-option :label="item" :value="item" v-for="item in templateTypeList" :key="item"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="作者" prop="author">
                <el-input v-model="form.author" placeholder="yourName" :disabled="formDisabled" style="max-width: 192px"></el-input>
              </el-form-item>
              <el-form-item label="包名" prop="packageName">
                <el-input v-model="form.packageName" placeholder="默认com.xxx" :disabled="formDisabled" style="max-width: 192px"></el-input>
              </el-form-item>
            </div>
            <el-form-item class="box-card-button">
              <el-button @click="resetForm('form')" :disabled="formDisabled">清除</el-button>
              <el-button @click="onGenerate" :disabled="formDisabled">预览</el-button>
              <el-button type="primary" @click="this.download" :disabled="formDisabled">下载</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
      <div class="content">
        <el-card class="box-card">
          <el-tabs v-model="selectedTab">
            <el-tab-pane
                v-for="item in editableTabs"
                :key="item.name"
                :label="item.title"
                :name="item.title"
            >
              <mavon-editor :value="item.content" defaultOpen="edit" :toolbars="toolbars" :editable="false" :toolbarsFlag="false" />
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
import {
  downloadCode,
  generateCode,
  getCreateTableSql,
  getSchema,
  getTableNameList,
  getTemplateTypeList
} from "@/api/biz/generator";
import {mergeRecursive} from "@/utils/ruoyi";

export default {
  name: 'Generator',
  components: {
  },
  data() {
    return {
      schemaData: [],
      selectedSchema: "",
      templateTypeList: [],
      tableData: [],
      isTableDataEmpty: true,
      formDisabled: true,
      form: {
        schemaName: '',
        tableName: '',
        templateTypes: [],
        author: '',
        packageName: '',
      },
      rules: {
        tableName: [{required: true, message: '请选择表名'}],
        templateTypes: [{required: true, message: '请选择生成模板'}],
      },
      editableTabs: [{
        title: 'DDL',
        content: '请先指定数据库'
      }],
      selectedTab: "DDL",
      toolbars: {
        help: true,
        preview: true
      }
    }
  },
  created() {
    this.initSchema();
    this.initTemplateTypeList();
  },
  methods: {
    initSchema() {
      getSchema().then(data => {
        this.schemaData = [];
        for (let i = 0; i < data.length; i++) {
          this.schemaData.push({name: data[i]})
        }
      }).catch(() => {});
    },
    initTemplateTypeList() {
      getTemplateTypeList().then(data => {
        this.templateTypeList = data;
      }).catch(() => {});
    },
    handleCurrentChange(row) {
      this.getTableNameListBySchema(row.name);
      this.resetForm();
    },
    getTableNameListBySchema(schemaName) {
      this.selectedSchema = schemaName;
      getTableNameList({
        schemaName: this.selectedSchema
      }).then(data => {
        this.tableData = data;
        this.isTableDataEmpty = false;
        this.editableTabs[0].content = '选择要生成的数据表';
      })
    },
    changeSchema(newSchema) {
      this.getTableNameListBySchema(newSchema);
    },
    resetForm() {
      this.$nextTick(() => {
        this.$refs['form'].resetFields();
      });
      this.clearTabs();
    },
    changeTable(newTable) {
      getCreateTableSql({
        schemaName: this.selectedSchema,
        tableName: newTable
      }).then(data => {
        this.formDisabled = false;
        this.editableTabs[0].content = data;
        this.selectedTab = "DDL";
        this.editableTabs.splice(1, this.editableTabs.length - 1);
      })
    },
    onGenerate() {
      this.$refs['form'].validate((valid) => {
        if (!valid) {
          return;
        }

        let param = mergeRecursive(this.form, {schemaName: this.selectedSchema});
        generateCode(param).then(data => {
          this.editableTabs.splice(1, this.editableTabs.length - 1);
          for (let key in data) {
            this.editableTabs.push({
              title: key,
              content: data[key]
            });
          }
          this.selectedTab = this.editableTabs[1].title;
        }).catch(() => {});
      });
    },
    download() {
      this.$refs['form'].validate((valid) => {
        if (!valid) {
          return;
        }

        let param = mergeRecursive(this.form, {schemaName: this.selectedSchema});
        downloadCode(param);
      });
    },
    clearTabs() {
      this.editableTabs[0].content = '选择要生成的数据表';
      this.editableTabs.splice(1, this.editableTabs.length - 1);
      this.selectedTab = "DDL";
    },
  }
}
</script>

<style lang="less" scoped>
.generator {
  padding: 10px;
  
  display: flex;
  justify-content: center;
  
  .left {
    display: none;

    @media (min-width: 992px) {
      display: block;
    }

    @media (min-width: 1280px) {
      display: block;
    }
    
    .box-card {
      min-width: 200px;
      height: 100%;

      .box-card-table {
        margin-top: 10px;
      }

      .left-list-item:not(:first-child) {
        margin-top: 10px;
      }
    }
  }
  
  .right {
    margin-left: 5px;

    .condition {
      .box-card {
        min-width: 450px;
        
        .el-form-item {
          margin-bottom: 0;
        }
        
        .box-card-form {
          display: grid;
          grid-template-columns: repeat(1, 1fr);
          grid-gap: 0;
          
          .form-select-schema {
            display: block;
          }

          @media (min-width: 768px) {
            min-width: 700px;
            grid-template-columns: repeat(2, 1fr);
          }

          @media (min-width: 992px) {
            grid-template-columns: repeat(3, 1fr);
            .form-select-schema {
              display: none;
            }
          }

          @media (min-width: 1280px) {
            grid-template-columns: repeat(4, 1fr);
            .form-select-schema {
              display: none;
            }
          }
        }
        
        .box-card-button {
          display: flex;
          flex-direction: row-reverse;
          
          margin-top: 15px;
        }
      }
    }

    .content {
      margin-top: 5px;
      
      .box-card {
        padding: 10px;
        height: 100%;
        
        min-height: 600px;
        max-width: 450px;
        
        @media (min-width: 768px) {
          max-width: 720px;
        }
        
        @media (min-width: 992px) {
          max-width: 850px;
        }

        @media (min-width: 1280px) {
          max-width: 1100px;
        }
      }
    }
  }
  
}

/deep/.el-card__body {
  padding: 10px;
}

/deep/.el-table td.el-table__cell {
  border: none;
}

/deep/.el-textarea__inner {
  height: 500px;
  //resize: none; /* 禁止用户手动调整大小 */
  overflow: auto; /* 防止内容溢出 */
}

.v-note-wrapper {
  min-height: 0;
}

</style>
