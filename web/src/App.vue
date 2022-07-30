<template>
  <NavBar />
  <router-view/>
</template>

<script>
import NavBar from "@/components/NavBar.vue"
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
// import $ from 'jquery'

export default {
  components: {
    NavBar,
  },
  setup() {
    // $.ajax({
    //   url: "http://localhost:3000/user/account/token/",
    //   type: "post",
    //   data: {
    //     username: "tongering",
    //     password: "123tongering",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   },
    // });
    // $.ajax({
    //   url: "http://localhost:3000/user/account/info/",
    //   type: "get",
    //   headers: {
    //     Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzMzZkNmE4NTU2YmE0YmJhYjQxNWYzOWNjMDI0MmI2ZiIsInN1YiI6IjIiLCJpc3MiOiJzZyIsImlhdCI6MTY1OTAwNDI5MiwiZXhwIjoxNjYwMjEzODkyfQ.z2WYERqbFuD8oVlavdAE8e4pJyC2lshDqZTSPw80FaQ",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   },
    // });
    // $.ajax({
    //   url: "http://localhost:3000/user/account/register/",
    //   type: "post",
    //   data: {
    //     username: "Tongering1",
    //     password: "123Tongering",
    //     confirmedPassword: "123Tongering",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   },
    // });
  }
}

</script>

<style>
body{
  background-image: url("@/assets/images/background.jpg");
  background-size: cover;
}
</style>
