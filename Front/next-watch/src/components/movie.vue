<template>
  <div>
    <navbar></navbar>
    <v-container>
      <v-layout justify-center row>
        <v-flex xs4 class="moviedesc" ma-2>
          <v-card height=480>
            <br>
            <img class="centered " :src="Poster" width=260 height=390/>
            <v-rating small half-increments length=10 readonly class="centered"
                      v-model="imdbRating"></v-rating>
          </v-card>
        </v-flex>
        <v-flex xs4 ma-2>
          <v-card>
            <v-card-text class="text-xs-left">
              <h2 class="text-xs-center">{{Title}} ({{Year}})</h2>
              <p>Genre: {{Genre}}</p>
              <p>Runtime: {{Runtime}}</p>
              <p>Plot: {{Plot}}</p>
              <p>Actors:{{Actors}}</p>
              <p>Director:{{Director}}</p>
              <p>Writers:{{Writer}}</p>
              <p>Language: {{Language}}</p>

            </v-card-text>

          </v-card>

        </v-flex>
      </v-layout>
      <v-layout justify-center row v-if="li">
        <v-flex mt-3>
          <v-btn large color="success" id="likedBtn" v-if="user.liked" @click="likedClick()">Unlike</v-btn>
          <v-btn large color="success" id="likeBtn" v-else @click="likedClick()">Like</v-btn>
          <v-btn large color="error" id="dislikedBtn" v-if="user.disliked" @click="dislikedClick()">Undislike</v-btn>
          <v-btn large color="error" id="dislikeBtn" v-else @click="dislikedClick()">Dislike</v-btn>
          <v-btn large color="warning" id="watchedBtn" :disabled="user.liked || user.disliked" v-if="user.watched" @click="watchedClick()">Unwatch</v-btn>
          <v-btn large color="warning" id="watchBtn" :disabled="user.liked || user.disliked" v-else @click="watchedClick()">Watch</v-btn>
          <v-btn large color="primary" id="watchedLaterBtn" v-if="user.watchLater" @click="watchLaterClick()">Don't Watch</v-btn>
          <v-btn large color="primary" id="watchLaterBtn" v-else @click="watchLaterClick()">Watch Later</v-btn>
        </v-flex>
      </v-layout>

      <v-layout justify-center row>
        <v-flex mt-3>
          <h4>Similar movies</h4>
        </v-flex>

      </v-layout>
      <v-layout justify-center row v-for="movie in similarMoviesShow" :key="movie.id">
        <v-flex xs8>
          <v-list>
            <v-list-tile @click="viewSimilarMovies(movie.id)">
              <v-list-tile-avatar>
                <img :src="movie.Poster">
              </v-list-tile-avatar>
              <v-list-tile-content>
                <v-list-tile-title>{{movie.Title}}</v-list-tile-title>
              </v-list-tile-content>
              <v-rating justify-end readonly small length="10" class="centered"
                        v-model="movie.imdbRating"></v-rating>
            </v-list-tile>
          </v-list>
        </v-flex>
      </v-layout>
      <v-layout justify-center row>
        <v-flex xs8>
          <v-btn v-if="currentPage>1" @click="prevPageSimilar">Previous</v-btn>
          <v-btn v-if="currentPage<4" @click="nextPageSimilar">Next</v-btn>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>
  import navbar from "./navbar.vue";

    export default {
        name: "Movie",
        components: {
            navbar
        },
        data: function () {
            return {
                id: "",
                Poster: "",
                imdbRating:
                    4.5,
                Title:
                    "",
                Plot:
                    "",
                Year:
                    0,
                Actors:
                    "",
                Director:
                    "",
                Writer:
                    "",
                Production:
                    "",
                Rated:
                    "",
                Runtime:
                    "",
                Genre:
                    "",
                Language:
                    "",
                Country:
                    "",
                li:
                    false,
                user:
                    {},
                similarMovies:
                    [],
                similarMoviesShow:
                    [],
                currentPage:
                    1
            }
        },
        mounted() {
            var url = window.location.pathname;
            this.axios.get("http://localhost:8080/api" + url).then(response => {
                this.id = response.data.id;
                this.Poster = response.data.Poster;
                this.imdbRating = response.data.imdbRating;
                this.Title = response.data.Title;
                this.Plot = response.data.Plot;
                this.Year = response.data.Year;
                this.Actors = response.data.Actors;
                this.Director = response.data.Director;
                this.Writer = response.data.Writer;
                this.Production = response.data.Production;
                this.Rated = response.data.Rated;
                this.Runtime = response.data.Runtime;
                this.Genre = response.data.Genre;
                this.Language = response.data.Language;
                this.Country = response.data.Country;
                this.getSimilar();
            });
            this.li = localStorage.getItem("jwt") !== "";
            if (this.li === true) {
                var getJwtToken = function () {
                    return localStorage.getItem('jwt');
                };
                this.axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();

                var movieID = url.split("/");
                this.axios.get("http://localhost:8080/api/getUserMovieRelations/" + movieID[2])
                    .then(response => {
                        this.user = response.data;
                    });
            }

        },
        methods: {
            likedClick: function () {
                var getJwtToken = function () {
                    return localStorage.getItem('jwt');
                };
                this.axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
                if (!this.user.liked) {
                    this.user.liked=true;
                    this.user.disliked=false;
                    this.axios.post("http://localhost:8080/api/movieLiked/" + this.id);
                } else if (this.user.liked) {
                    this.user.liked=false;
                    this.axios.post("http://localhost:8080/api/movieUnliked/" + this.id);
                }
            },
            dislikedClick: function () {
                var getJwtToken = function () {
                    return localStorage.getItem('jwt');
                };
                this.axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
                if (!this.user.disliked) {
                    this.user.disliked=true;
                    this.user.liked=false;
                    this.axios.post("http://localhost:8080/api/movieDisliked/"+ this.id);
                } else if (this.user.disliked) {
                    this.user.disliked=false;
                    this.axios.post("http://localhost:8080/api/movieUndisliked/"+ this.id);
                }
            },
            watchedClick: function () {
                var getJwtToken = function () {
                    return localStorage.getItem('jwt');
                };
                this.axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
                if (!this.user.watched) {
                    this.user.watched = true;
                    this.axios.post("http://localhost:8080/api/movieWatched/"+ this.id);
                } else if (this.user.watched) {
                    this.user.watched=false;
                    this.axios.post("http://localhost:8080/api/movieUnwatched/"+ this.id);
                }
            },
            watchLaterClick: function () {
                var getJwtToken = function () {
                    return localStorage.getItem('jwt');
                };
                this.axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
                if (!this.user.watchLater) {
                    this.user.watchLater=true;
                    this.axios.post("http://localhost:8080/api/movieWatchLater/"+ this.id);
                } else if (this.user.watchLater) {
                    this.user.watchLater = false;
                    this.axios.post("http://localhost:8080/api/movieUnwatchLater/"+ this.id);
                }
            },
            viewSimilarMovies(id) {
                this.$router.push('/movie/' + id);
                var url = window.location.pathname;
                this.axios.get("http://localhost:8080/api" + url).then(response => {
                    this.id = response.data.id;
                    this.Poster = response.data.Poster;
                    this.imdbRating = response.data.imdbRating;
                    this.Title = response.data.Title;
                    this.Plot = response.data.Plot;
                    this.Year = response.data.Year;
                    this.Actors = response.data.Actors;
                    this.Director = response.data.Director;
                    this.Writer = response.data.Writer;
                    this.Production = response.data.Production;
                    this.Rated = response.data.Rated;
                    this.Runtime = response.data.Runtime;
                    this.Genre = response.data.Genre;
                    this.Language = response.data.Language;
                    this.Country = response.data.Country;
                    this.getSimilar();
                });


            }
            ,
            showSimilarMovies() {
                this.similarMoviesShow = [];
                for (var i = 0; i < 5; i++) {
                    if (this.similarMovies[(this.currentPage - 1) * 5 + i] != undefined) {
                        this.similarMoviesShow.push(this.similarMovies[(this.currentPage - 1) * 5 + i]);
                    }
                }
            },
            prevPageSimilar() {
                this.currentPage--;
                this.showSimilarMovies();
            }
            ,
            nextPageSimilar() {
                this.currentPage++;
                this.showSimilarMovies()
            }
            ,
            getSimilar() {
                this.similarMoviesShow = [];
                this.axios.get("http://localhost:8080/api/getSimilar/" + this.id).then(response => {
                    this.similarMovies = response.data;
                    this.showSimilarMovies();

                });
            },
            updated: function () {
                this.$nextTick(function () {
                    // Code that will run only after the
                    // entire view has been re-rendered
                })
            }

        }

    }
</script>
