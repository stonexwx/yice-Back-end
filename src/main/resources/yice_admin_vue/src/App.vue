<template>
  <div id="app">
    <router-view v-if="isReloadAlive"/>
  </div>
</template>

<script>
export default {
  provide() {
    return {
      reload: this.reload
    };
  },
  data() {
    return {
      isReloadAlive: true
    }
  },
  methods: {
    reload() {
      this.isReloadAlive = false;
      this.$nextTick(function () {
        this.isReloadAlive = true;
      });
    }
  }
};
</script>

<style lang="css">
/**修改全局的滚动条*/
/**滚动条的宽度*/
::-webkit-scrollbar {
  width: 0px;

}

::-webkit-scrollbar-thumb {
  background-color: rgba(238, 238, 238, 0.47);
  border-radius: 3px;
}
</style>
