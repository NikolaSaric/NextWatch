<template>
  <v-container v-if="foundMovies">
    <v-layout align-start row justify-start class="UpperLowerMargin5">
      <span class="smallMarginRight font-weight-medium title">Found Movies: </span>
    </v-layout>

    <v-layout row v-for="movie in foundMovies" :key="movie.id">
      <v-flex xs6 order-lg2>
        <moviePreview :rating="movie.rating" :filePath="movie.poster" :movieName="movie.title" ></moviePreview>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
    import moviePreview from "./moviePreview.vue";
    //import { dataBus } from '../main';

    export default {
        name: "searchResults.vue",
        props: ["searchTitle"],
        components:{
            moviePreview
        },
        data: function () {
            return {
                foundMovies: [],
            }
        },
        mounted() {
            this.axios.get("http://localhost:8080/api/searchMovies/" + this.$route.params.searchTitle)
                .then(response => {
                    this.foundMovies = response.data;
                });
        },
        watch: {
            "$route.params.searchTitle"(newTitle) {
                // react to route changes...
                this.axios.get("http://localhost:8080/api/searchMovies/" + newTitle)
                    .then(response => {
                        this.foundMovies = response.data;
                    });
            }
        }
    }
</script>

<style scoped>

</style>