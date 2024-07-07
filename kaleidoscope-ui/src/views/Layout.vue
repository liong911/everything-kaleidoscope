<template>
  <div>
    <div class="page-header">
      <page-header/>
    </div>
    <div class="page-container">
      <router-view/>
    </div>
    <div class="page-footer">
      <page-footer/>
    </div>
  </div>
</template>

<script>
import PageHeader from "@/components/PageHeader.vue";
import PageFooter from "@/components/PageFooter.vue";

export default {
  name: 'Layout',
  components: {
    PageHeader,
    PageFooter,
  },
  data() {
    return {
    }
  }
}
</script>

<style less="less" scoped>

</style>