<template>
  <div>
    <v-container>
      <v-layout justify-center row>
        <v-flex xs4 class="moviedesc" ma-2>
          <v-card height=480>
            <br>
            <img class="centered " :src="Poster" width=260 height=390/>
            <v-rating small half-increments length=10 readonly class="centered" v-model="imdbRating"></v-rating>
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
            <v-btn large color="success" id="likeBtn" @click="likedClick()">Like</v-btn>
            <v-btn large color="error">Dislike</v-btn>
            <v-btn large color="warning">Watched</v-btn>
            <v-btn large color="primary">Watch Later</v-btn>
        </v-flex>
      </v-layout>
      <v-layout justify-center row>
        <v-flex mt-3>
          <h4>Similar movies</h4>
        </v-flex>

      </v-layout>
      <v-layout justify-center row>
        <v-flex xs8>
          <v-list>
            <v-list-tile>
              <v-list-tile-avatar>
                <img :src="Poster">
              </v-list-tile-avatar>
              <v-list-tile-content>
                <v-list-tile-title>Lotr2</v-list-tile-title>
              </v-list-tile-content>
              <v-rating class="centered" v-model="imdbRating"></v-rating>
            </v-list-tile>
          </v-list>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>
    export default {
        name: "Movie",
        data: function () {
            return {
                Poster: "",
                imdbRating: 4.5,
                Title: "",
                Plot: "",
                Year: 0,
                Actors: "",
                Director: "",
                Writer: "",
                Production: "",
                Rated: "",
                Runtime: "",
                Genre: "",
                Language: "",
                Country: "",
                li: false,
                liked: false,
                disliked: false,
                watched: false,
                wactchLater: false,
                user: {}
            }
        },
        mounted() {
            this.li = localStorage.getItem("jwt") !== "";
            if(this.li === true) {
                var getJwtToken = function() {
                    return localStorage.getItem('jwt');
                };
                this.axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
                console.log(this.axios.defaults.headers.common['Authorization']);
                this.axios.get("http://localhost:8080/api/getUserMovies")
                    .then(response => {
                        this.user = response.data;
                    });
                if(this.Title in this.user.likedMovies) {
                    this.liked = true;
                    this.watched = true;
                    document.getElementById("likeBtn").textContent = "Lied";
                } else if(this.Title in this.user.dislikedMovies) {
                    this.disliked = true;
                    this.watched = true;
                } else if(this.Title in this.user.watchedMovies) {
                    this.watched = true;
                } else if(this.Title in this.user.watchLaterMovies) {
                    this.wactchLater = true;
                }

            }
            var url = window.location.pathname;
            this.axios.get("http://localhost:8080/api" + url).then(response => {
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
            });
        },
        methods: {
            likedClick: function () {
                this.liked = true;
                document.getElementById("likeBtn").textContent = "Liked";
            }
        }

    }
</script>
