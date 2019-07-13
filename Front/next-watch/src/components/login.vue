<template>
  <div>
    <h1>Welcome to our LogIn page</h1>
    <table>
      <tr>
        <td>Enter Username: </td>
        <td><label>
          <input type="text" v-model="username">
        </label></td>
      </tr>
      <tr>
        <td>Enter Password: </td>
        <td><label>
          <input type="text" v-model="password">
        </label></td>
      </tr>
      <tr>
        <td></td>
        <td><button v-on:click=""></button></td>
      </tr>
    </table>
  </div>
</template>

<script>
    export default {
        name: "login.vue",
        data: function () {
            return {
                username: "",
                password: ""
            }
        },
        methods: {
            login: function () {
                if(this.username === "") {
                    alert("Please enter username.");
                    return;
                }
                if(this.passive === "") {
                    alert("Please enter password.");
                    return;
                }
                Vue.axios.post("")
            }
        }
    }
</script>

<style scoped>

</style>