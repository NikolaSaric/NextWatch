<template>
  <div>
    <v-container>
      <div v-if="li">
        <v-layout align-start row justify-start class="UpperLowerMargin5">
          <span class="smallMarginRight font-weight-medium title">Recomended to you</span>
          <v-btn>More</v-btn>

        </v-layout>

        <v-layout row>
          <v-flex xs6 order-lg2>
            <moviePreview rating=5
                          filePath="https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_SX300"
                          movieName="The Lord of the Rings: The Fellowship of the Ring"></moviePreview>
          </v-flex>
        </v-layout>
      </div>

      <v-layout align-start row justify-start class="UpperLowerMargin5">
        <span class="smallMarginRight font-weight-medium title">Popular</span>
        <v-btn @click="popPrevious">
          <v-icon>arrow_left</v-icon>
        </v-btn>
        <v-btn @click="popNext">
          <v-icon>arrow_right</v-icon>
        </v-btn>
        <v-btn>More</v-btn>
      </v-layout>


      <v-layout row>
        <v-flex xs6 order-lg2 v-for="popMovie in popularShow" :key="popMovie.id">
          <moviePreview :rating=popMovie.imdbRating :filePath=popMovie.Poster :movieName=popMovie.Title
                        :id="popMovie.id"></moviePreview>
        </v-flex>
      </v-layout>

      <v-layout align-start row justify-start class="UpperLowerMargin5">
        <span class="smallMarginRight font-weight-medium title">New</span>
        <v-btn @click="newPrevious">
          <v-icon>arrow_left</v-icon>
        </v-btn>
        <v-btn @click="newNext">
          <v-icon>arrow_right</v-icon>
        </v-btn>
        <v-btn>More</v-btn>
      </v-layout>

      <v-layout row>
        <v-flex xs6 order-lg2 v-for="newMovie in newestShow" :key="newMovie.id">
          <moviePreview :rating=newMovie.imdbRating :filePath=newMovie.Poster :movieName=newMovie.Title
                        :id="newMovie.id"></moviePreview>
        </v-flex>

      </v-layout>
    </v-container>
  </div>
</template>

<script>
    import moviePreview from "./moviePreview.vue";

    export default {
        name: 'Index',
        components: {
            moviePreview
        },
        data() {
            return {
                newest: [],
                newestPageNum: 1,
                newestShow: [],
                popular: [],
                popularPageNum: 1,
                popularShow: [],
                li: false
            }
        },
        methods: {
            newPrevious() {
                this.newestPageNum--;
                if (this.newestPageNum == 0) {
                    this.newestPageNum = 4;
                }
                if (this.newest[(this.newestPageNum - 1) * 5] == undefined) {
                    this.newestPageNum++;
                }
                var list = [];
                for (var i = 0; i < 5; i++) {
                    if (this.newest[(this.newestPageNum - 1) * 5 + i] == undefined) {
                        break;
                    }
                    list.push(this.newest[(this.newestPageNum - 1) * 5 + i]);
                }
                this.newestShow = list;


            },
            newNext() {
                this.newestPageNum++;
                if (this.newestPageNum == 5) {
                    this.newestPageNum = 1;
                }
                if (this.newest[(this.newestPageNum - 1) * 5] == undefined) {
                    this.newestPageNum--;
                }
                var list = [];
                for (var i = 0; i < 5; i++) {
                    if (this.newest[(this.newestPageNum - 1) * 5 + i] == undefined) {
                        break;
                    }
                    list.push(this.newest[(this.newestPageNum - 1) * 5 + i]);
                }
                this.newestShow = list;

            },
            popPrevious() {
                this.popularPageNum--;
                if (this.popularPageNum == 0) {
                    this.popularPageNum = 4;
                }
                if (this.popular[(this.popularPageNum - 1) * 5] == undefined) {
                    this.popularPageNum++;
                }
                var list = [];
                for (var i = 0; i < 5; i++) {
                    if (this.popular[(this.popularPageNum - 1) * 5 + i] == undefined) {
                        break;
                    }
                    list.push(this.popular[(this.popularPageNum - 1) * 5 + i]);
                }
                this.popularShow = list;


            },
            popNext() {
                this.popularPageNum++;
                if (this.popularPageNum == 5) {
                    this.popularPageNum = 1;
                }
                if (this.popular[(this.popularPageNum - 1) * 5] == undefined) {
                    this.popularPageNum--;
                }
                var list = [];
                for (var i = 0; i < 5; i++) {
                    if (this.popular[(this.popularPageNum - 1) * 5 + i] == undefined) {
                        break;
                    }
                    list.push(this.popular[(this.popularPageNum - 1) * 5 + i]);
                }
                this.popularShow = list;

            }
        },
        mounted() {
            this.li = localStorage.getItem("jwt") !== "";
            this.axios.get("http://localhost:8080/api/getNewest").then(response => {
                this.newest = response.data;
                for (var i = 0; i < 5; i++) {
                    if (this.newest[i] == undefined) {
                        break;
                    }
                    this.newestShow.push(this.newest[i]);
                }
            });
            this.axios.get("http://localhost:8080/api/getPopular").then(response => {
                this.popular = response.data;
                for (var i = 0; i < 5; i++) {
                    if (this.popular[i] == undefined) {
                        break;
                    }
                    this.popularShow.push(this.popular[i]);
                }
            });
        }
    }
</script>