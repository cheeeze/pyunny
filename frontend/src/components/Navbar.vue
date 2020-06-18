<template>
  <div class="nav-wrapper">
    <div class="grad-bar"></div>
    <nav class="main-navbar">
      <router-link to="/">
        <img class="nav_logo" src="@/assets/images/mainlogo_removebg.png" alt="Logo" />
      </router-link>
      <div class="menu-toggle" id="mobile-menu">
        <span class="bar"></span>
        <span class="bar"></span>
        <span class="bar"></span>
      </div>
      <ul class="nav no-search">
        <li class="nav-item">
          <router-link to="/home">Home</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/map">Map</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/recipe">Recipe</router-link>
        </li>
        <li class="nav-item" v-if="userId!=0">
          <router-link to="/mypage/">My Page</router-link>
        </li>
        <li class="nav-item" v-if="userId==0">
          <button id="login-btn" @click="handleClickButton">
            <img src="@/assets/icons/sign-in.svg" alt width="22px" />
          </button>
        </li>
        <li class="nav-item" v-if="userId!=0">
          <button id="login-btn" @click="logout">
            <img src="@/assets/icons/logout.png" alt width="22px" />
          </button>
        </li>
        <li class="nav-item">
          <button id="barcode-btn" @click="handleClickBarcode">
            <img src="@/assets/icons/redbarcode.png" alt width="30px" />
          </button>
        </li>
      </ul>
    </nav>
    <user-sign-modal :visible.sync="visible" style="z-index: 80;"></user-sign-modal>
    <barcode-modal :barcode.sync="barcode"></barcode-modal>
  </div>
</template>
<script>
import UserSignModal from "@/components/LoginModal.vue";
import BarcodeModal from "@/components/BarcodeModal.vue";
import $ from "jquery";

$(document).ready(function() {
  $("#search-icon").click(function() {
    $(".nav").toggleClass("search");
    $(".nav").toggleClass("no-search");
    $(".search-input").toggleClass("search-active");
  });

  $(".menu-toggle").click(function() {
    $(".nav").toggleClass("mobile-nav");
    $(this).toggleClass("is-active");
  });
});

export default {
  data() {
    return {
      visible: false,
      barcode: false,
      userId: 0
    };
  },
  components: {
    UserSignModal,
    BarcodeModal
  },
  mounted() {
    //console.log(sessionStorage.getItem("user"));
    if (sessionStorage.getItem("user") != null) {
      //console.log("dd");
      this.userId = JSON.parse(sessionStorage.getItem("user"));
    }
  },
  methods: {
    handleClickButton() {
      this.visible = !this.visible;
      console.log("hello");
    },
    handleClickBarcode() {
      if (this.userId == 0) {
        return alert("로그인후 이용가능합니다.");
      }

      this.barcode = !this.barcode;
      //console.log("hello");
    },
    logout() {
      sessionStorage.removeItem("user");
      this.userId = 0;
      if (this.$route.path != "/") {
        this.$router.push("/");
      }
    }
  }
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* BASIC SETUP */
#login-btn {
  border: 0;
  outline: none;
  background-color: transparent;
  cursor: pointer;
}

#barcode-btn {
  border: none;
  outline: none;
  background-color: transparent;
  cursor: pointer;
}

.nav-wrapper {
  width: 100%;
  position: -webkit-sticky; /* Safari */
  position: fixed;
  top: 0;
  background-color: #fff;
  box-shadow: 5px 0px 5px gray;
  z-index: 30;
}

.grad-bar {
  width: 100%;
  height: 5px;
  background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
  background-size: 400% 400%;
  -webkit-animation: gradbar 15s ease infinite;
  -moz-animation: gradbar 15s ease infinite;
  animation: gradbar 15s ease infinite;
}

/* NAVIGATION */

.main-navbar {
  display: grid;
  grid-template-columns: 1fr 3fr;
  align-items: center;
  height: 55px;
  overflow: hidden;
  font-family: "Lato", "Arial", sans-serif;
}

.main-navbar .nav_logo {
  height: 40px;
  width: auto;
  justify-self: start;
}

.main-navbar ul {
  list-style: none;
  display: grid;
  grid-template-columns: repeat(6, 1fr);
  justify-self: end;
}

.nav-item a {
  color: #000;
  font-size: 0.9rem;
  font-weight: 400;
  text-decoration: none;
  transition: color 0.3s ease-out;
}

.nav-item a:hover {
  color: #3498db;
}

/* SECTIONS */

.headline {
  width: 100%;
  height: 50vh;
  min-height: 350px;
  background: linear-gradient(rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.7)),
    url("https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/jPLiQ-9/mountains-background-for-titles-intro-projects-and-etc_ew22rur3l__F0000.png");
  background-size: cover;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.features {
  width: 100%;
  height: auto;
  background-color: #f1f1f1;
  display: flex;
  padding: 50px 20px;
  justify-content: space-around;
}

.feature-container {
  flex-basis: 30%;
  margin-top: 10px;
}

.feature-container p {
  color: #000;
  text-align: center;
  line-height: 1.4;
  margin-bottom: 15px;
}

.feature-container img {
  width: 100%;
  margin-bottom: 15px;
}

/* MOBILE MENU & ANIMATION */

.menu-toggle .bar {
  width: 25px;
  height: 3px;
  background-color: #3f3f3f;
  margin: 5px auto;
  -webkit-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}

.menu-toggle {
  justify-self: end;
  margin-right: 25px;
  display: none;
}

.menu-toggle:hover {
  cursor: pointer;
}

#mobile-menu.is-active .bar:nth-child(2) {
  opacity: 0;
}

#mobile-menu.is-active .bar:nth-child(1) {
  -webkit-transform: translateY(8px) rotate(45deg);
  -ms-transform: translateY(8px) rotate(45deg);
  -o-transform: translateY(8px) rotate(45deg);
  transform: translateY(8px) rotate(45deg);
}

#mobile-menu.is-active .bar:nth-child(3) {
  -webkit-transform: translateY(-8px) rotate(-45deg);
  -ms-transform: translateY(-8px) rotate(-45deg);
  -o-transform: translateY(-8px) rotate(-45deg);
  transform: translateY(-8px) rotate(-45deg);
}

/* KEYFRAME ANIMATIONS */

@-webkit-keyframes gradbar {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}

@-moz-keyframes gradbar {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}

@keyframes gradbar {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}

/* Media Queries */

/* Mobile Devices - Phones/Tablets */

@media only screen and (max-width: 720px) {
  .features {
    flex-direction: column;
    padding: 50px;
  }

  /* MOBILE NAVIGATION */

  .main-navbar ul {
    display: flex;
    flex-direction: column;
    position: fixed;
    justify-content: start;
    top: 55px;
    background-color: #fff;
    width: 100%;
    height: calc(100vh - 55px);
    transform: translate(-101%);
    text-align: center;
    overflow: hidden;
  }

  .main-navbar li {
    padding: 15px;
  }

  .main-navbar li:first-child {
    margin-top: 50px;
  }

  .main-navbar li a {
    font-size: 1rem;
  }

  .menu-toggle,
  .bar {
    display: block;
    cursor: pointer;
  }

  .mobile-nav {
    transform: translate(0%) !important;
  }

  /* SECTIONS */

  .headline {
    height: 20vh;
  }

  .feature-container p {
    margin-bottom: 25px;
  }

  .feature-container {
    margin-top: 20px;
  }

  .feature-container:nth-child(2) {
    order: -1;
  }
}
</style>
