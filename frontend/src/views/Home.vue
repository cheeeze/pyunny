<template>
  <div style="margin-top: 55px;">
    <navbar style="z-index:50;"></navbar>
    <div class="wrapper">
      <!--왼쪽 화면 부분-->
      <div class="main-container part1">
        <div class="cover-holder" style="text-align: justify">
          <h1 class="part1-h1" style="margin: 1vw 0 0;">편의점의 모든 것</h1>
          <img
            class="main_logo"
            src="@/assets/images/mainlogo_removebg.png"
            alt=""
            style="margin-bottom: 30px;"
          />
          <p style="margin-bottom: 0px;">
            모든 편의점을 한 곳에서<br />간편하게 관리하세요!
          </p>
        </div>
      </div>
      <!---->
      <!--오른쪽 화면 부분-->
      <div class="main-container part2" style="z-index: 10;">
        <main-info style="z-index: 15;"></main-info>
      </div>
      <!---->
    </div>
  </div>
</template>

<script>
import MainInfo from "@/components/MainInfo.vue";
import Navbar from "@/components/Navbar.vue";

export default {
  components: {
    Navbar,
    MainInfo,
  },
};
</script>
<style lang="less" scoped>
@font-face {
  font-family: "Kyobo";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/KyoboHand.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

* {
  font-family: "Kyobo";
}

.color1 {
  background: #536dfe;
}
.color2 {
  background: #212121;
}
.color3 {
  background: #d32f2f;
}
.color4 {
  background: #e64a19;
}
html,
body {
  height: 100%;
}
body {
  font-size: 100%;
  line-height: 1.5;
  font-family: "Kyobo", sans-serif;
}

*,
*:before,
*:after {
  box-sizing: border-box;
}

.main_logo {
  max-width: 100%;
  height: auto;
  vertical-align: baseline;
}

a {
  text-decoration: none;
}

.max(@maxWidth;
  @rules) {
  @media only screen and (max-width: @maxWidth) {
    @rules();
  }
}

.min(@minWidth;
  @rules) {
  @media only screen and (min-width: @minWidth) {
    @rules();
  }
}
.wrapper {
  max-width: 1920px;
  margin: 0 auto;
}
.main-container {
  padding: 2em 0 0.5em;
  // overflow: hidden;
}
.part1 {
  background-image: url("../assets/images/mainimg.png");
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center center;
  display: flex;
  flex-flow: column;
}
.cover-holder {
  margin: 0 auto;
  max-width: 300px;
  padding: 0 12px;
  // height: 100%;
  h1 {
    font-size: 2em;
    color: rgb(255, 255, 240);
    line-height: 1;
    text-align: center;
    text-transform: uppercase;
    font-weight: 100;
    margin-bottom: 12px;
    span {
      display: block;
      color: rgba(20, 20, 20, 0.45);
      font-weight: 700;
      font-size: 4rem;
    }
  }
  p {
    font-size: 0.925em;
    color: rgb(255, 255, 240);
    text-align: center;
    border-top: 1px solid rgb(240, 240, 255);
    border-bottom: 1px solid rgb(240, 240, 255);
    padding: 8px 0;
    font-weight: 300;
  }
}
.min(
  400px,
  {.cover-holder {h1 {font-size: 3em; span {font-size: 8rem;}} p {font-size: 1.5em;}}}
);

.max(450px, {.part1 {display: none;}});

.part2 {
  padding: 1rem;
  height: 100vh;
  display: flex;
}

.conv_icon {
  height: 60px;
  margin: 10px 2vw;
}

.min(
  900px,
  {.wrapper {.part1 {width: 40%; position: fixed; height: 100vh;} .part2 {width:
    60%; float: right;}}}
);
.min(1060px, {.wrapper {.part1 {width: 50%;} .part2 {width: 50%;}}});
</style>
