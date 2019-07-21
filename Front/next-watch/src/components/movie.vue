<template>
    <div>
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
    export default {
        name: "Movie",
        data: function () {
            return {
                id: 0,
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
                similarMovies: [],
                similarMoviesShow: [],
                currentPage: 1
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
        },
        methods: {
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


            },
            prevPageSimilar() {
                this.currentPage--;
                this.similarMoviesShow = [];
                for (var i = 0; i < 5; i++) {
                    this.similarMoviesShow.push(this.similarMovies[(this.currentPage - 1) * 5 + i]);
                }
            },
            nextPageSimilar() {
                this.currentPage++;
                this.similarMoviesShow = [];
                for (var i = 0; i < 5; i++) {
                    this.similarMoviesShow.push(this.similarMovies[(this.currentPage - 1) * 5 + i]);
                }
            },
            getSimilar(){
                this.axios.get("http://localhost:8080/api/getSimilar/" + this.id).then(response => {
                    this.similarMovies = response.data;
                    for (var i = 0; i < 5; i++) {
                        this.similarMoviesShow.push(this.similarMovies[i]);
                    }

                });
            }
        }

    }
</script>
