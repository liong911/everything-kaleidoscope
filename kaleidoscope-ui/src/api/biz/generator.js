import {default as request, download} from '@/utils/request'

// 查询库名(模式名)
export function getSchema() {
  return request({
    url: '/generator/getSchema',
    headers: {
      isToken: false
    },
    method: 'get',
    params: null
  })
}

// 查询模式下的表列表
export function getTableNameList(query) {
  return request({
    url: '/generator/getTableNameList',
    headers: {
      isToken: false
    },
    method: 'get',
    params: query
  })
}

// 查询建表sql
export function getCreateTableSql(query) {
  return request({
    url: '/generator/getCreateTableSql',
    headers: {
      isToken: false
    },
    method: 'get',
    params: query
  })
}

// 查询模板类型列表
export function getTemplateTypeList() {
  return request({
    url: '/generator/getTemplateTypeList',
    headers: {
      isToken: false
    },
    method: 'get',
    params: null
  })
}

// 生成Java代码
export function generateCode(query) {
  return request({
    url: '/generator/generateCode',
    headers: {
      isToken: false
    },
    method: 'post',
    data: query
  })
}

// 下载Java代码
export function downloadCode(query) {
  download('/generator/download', query);
}
