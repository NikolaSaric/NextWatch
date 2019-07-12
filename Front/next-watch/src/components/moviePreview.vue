<template>
  <div>
    <v-img class="centered" :src="filePath"  width=200 height=300 ></v-img>
    <v-rating  class="centered" v-model="rating"></v-rating>
    <h2 class="centered">{{movieName}}</h2>
  </div>
</template>

<script>
export default {
  name: 'Index',
  props:['filePath','movieName','rating','date','year','description'],
}
</script>