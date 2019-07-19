
<template>
  <div>
    <img class="centered" :src="filePath"  width=200 height=300 @click="$router.push('/movie/'+ id)"/>
    <v-rating length=10 small  class="centered" v-model="rating" ></v-rating>
    <h2 class="centered">{{movieName}}</h2>
  </div>
</template>

<script>
export default {
  name: 'Index',
  props:['filePath','movieName','rating','date','year','description','id'],
}
</script>