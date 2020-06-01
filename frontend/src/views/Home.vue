<template>
  <div>
    <navbar></navbar>
    <div class="wrapper">
      <div class="container part1">
        <div class="cover-holder" style="text-align: justify">
          <h1 style="margin: 1vw 0 30px;">
            편의점의 모든 것<span
              ><img
                class="main_logo"
                src="@/assets/images/mainlogo_removebg.png"
                alt=""
            /></span>
          </h1>
          <p style="margin-bottom: 0px;">
            모든 편의점을 한 곳에서<br />간편하게 관리하세요!
          </p>
        </div>
      </div>
      <div class="container part2">
        <h1>hello</h1>
        <div class="conv_icons" style="display: inline-block; height: 50px;">
          <img class="conv_icon" src="@/assets/icons/gs25.png" alt="" />
          <img class="conv_icon" src="@/assets/icons/cu.png" alt="" />
          <img class="conv_icon" src="@/assets/icons/emart.jpg" alt="" />
          <img class="conv_icon" src="@/assets/icons/seven.png" alt="" />
          <img class="conv_icon" src="@/assets/icons/ministop.png" alt="" />
        </div>
        <!-- </maininfo> -->
        <!-- <div class="story color1">
        <div class="picture-box">
          <img src="https://unsplash.it/600/?image=432" alt="" />
        </div>
        <div class="textbox">
          <h3>Australia</h3>
          <h2>Doug Graves</h2>
          <p>
            It was like something out of a film - miles from anywhere and
            being&hellip;
          </p>
        </div>
      </div>
      <div class="story color2">
        <div class="picture-box">
          <img src="https://unsplash.it/600/?image=484" alt="" />
        </div>
        <div class="textbox">
          <h3>New Zealand</h3>
          <h2>Wade Inlaykes</h2>
          <p>
            Sure, there were signs on the valley floor, but we were
            tired&hellip;
          </p>
        </div>
      </div>
      <div class="story color3">
        <div class="picture-box">
          <img src="https://unsplash.it/600/?image=538" alt="" />
        </div>
        <div class="textbox">
          <h3>Canada</h3>
          <h2>Chuck Mehdinner</h2>
          <p>
            So, we're a long way from anywhere and I figured if you can eat
            sushi&hellip;
          </p>
        </div>
      </div>
      <div class="story color1">
        <div class="picture-box">
          <img src="https://unsplash.it/600/?image=622" alt="" />
        </div>
        <div class="textbox">
          <h3>Brazil</h3>
          <h2>Dee Major</h2>
          <p>
            By this point it was so close to us we could see the colour of the
            eyes&hellip;
          </p>
        </div>
      </div>
      <div class="story color4">
        <div class="picture-box">
          <img src="https://unsplash.it/600/?image=124" alt="" />
        </div>
        <div class="textbox">
          <h3>Indonesia</h3>
          <h2>Carrie Oakey</h2>
          <p>
            By this point it was so close to us we could see the colour of the
            eyes&hellip;
          </p>
        </div>
      </div> -->
      </div>
    </div>
  </div>
</template>

<script>
// import MainInfo from "@/components/MainInfo.vue";
import Navbar from "@/components/Navbar.vue";

export default {
  components: {
    Navbar,
    // MainInfo,
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

.group {
  &:after {
    content: "";
    display: table;
    clear: both;
  }
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
.container {
  padding: 2em 0 0.5em;
  overflow: hidden;
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

.part2 {
  padding: 0;
}

.conv_icon {
  height: 60px;
  margin: 10px 2vw;
}

.story {
  display: flex;
  flex-flow: row wrap;
  border-bottom: 1px solid rgba(0, 0, 0, 0.35);
  .picture-box {
    position: relative;
    width: 100%;
    overflow: hidden;
    &:before {
      content: " ";
      display: block;
      padding-top: 100%;
    }
    // img {
    //   position: absolute;
    //   top: 0;
    //   left: 0;
    //   bottom: 0;
    //   right: 0;
    // }
  }
  .textbox {
    display: flex;
    width: 100%;
    flex-flow: column;
    padding: 2em;
    h3 {
      color: rgba(255, 255, 255, 0.7);
      font-size: 1.5em;
      line-height: 1;
      font-weight: 100;
    }
    h2 {
      color: rgba(255, 255, 255, 0.4);
      font-size: 2.5em;
      line-height: 1;
      font-weight: 700;
    }
    p {
      color: #fff;
      font-size: 1em;
      margin-top: auto;
    }
  }
}

.min(500px, {.story {flex-flow: row;} .picture-box, .textbox {width: 50%;}});
.min(
  900px,
  {.wrapper {.part1 {width: 40%; position: fixed; height: 100vh;} .part2 {width:
    60%; float: right;}}}
);
.min(1060px, {.wrapper {.part1 {width: 50%;} .part2 {width: 50%;}}});
</style>
