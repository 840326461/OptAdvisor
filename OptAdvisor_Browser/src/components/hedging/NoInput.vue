<template>
    <div>
      <p style="font-weight: bold;font-size: 25px">套期保值效果展示</p>
      <Logo style="margin-top: 100px"></Logo>
      <p style="font-size: 25px;-webkit-text-fill-color: #ff9900;margin-top: 20px">{{this.text}}</p>
    </div>
</template>

<script>
  import Logo from "../util/Logo";
    export default {
        name: "NoInput",
      components: {Logo},
      data(){
          return{
            text:'暂无数据，等待输入'
          }
      }
    }
</script>

<style scoped>

</style>
