<template>
  <div id="full">
    <navbar></navbar>
    <section id="banner" class="topbanner">
      <div id="boxes" :class="tempClass">
        <!-- red box -->
        <div class="small-box red" @click="filp('red')">
          <div>
            <a class="red-visible original">
              <span>기프티콘 관리</span>
            </a>

            <a class="blue-visible">
              <span>내가 쓴 레시피</span>
              <img src="@/assets/images/write_img.jpg" />
            </a>

            <a class="yellow-visible">
              <span>품목설정1</span>
            </a>

            <a class="darkgray-visible">
              <span>내 정보 수정</span>
              <img src="@/assets/images/person_img.jpg" />
            </a>
          </div>
        </div>

        <!-- blue-box -->
        <div class="small-box blue" rel="blue" @click="filp('blue')">
          <div>
            <a class="blue-visible original">
              <span>나만의 레시피 관리</span>
            </a>

            <a class="red-visible">
              <span>내가 가진 기프티콘</span>
              <img src="@/assets/images/more_img.jpg" />
            </a>

            <a class="yellow-visible">
              <span>품목설정2</span>
            </a>

            <a class="darkgray-visible">
              <span>설정3</span>
            </a>
          </div>
        </div>

        <!-- yellow-box -->
        <div class="small-box yellow" @click="filp('yellow')">
          <div>
            <a class="yellow-visible original">
              <span>관심품목</span>
            </a>

            <a class="red-visible" @click="selec('red1')">
              <span>기프티콘 등록</span>
              <img src="@/assets/images/plus_img.jpg" />
            </a>

            <a class="blue-visible">
              <span>내가 쓴 댓글</span>
              <img src="@/assets/images/comment_img.jpg" />
            </a>

            <a class="darkgray-visible">
              <span>멤버십 관리</span>
              <img src="@/assets/images/card_img.jpg" />
            </a>
          </div>
        </div>

        <!-- black-box -->
        <div
          class="small-box darkgray"
          rel="darkgray"
          @click="filp('darkgray')"
        >
          <div>
            <a class="darkgray-visible original">
              <span>설정</span>
            </a>

            <a class="red-visible">
              <span>사용한 기프티콘</span>
              <img src="@/assets/images/check_img.jpg" />
            </a>

            <a class="blue-visible">
              <span>좋아요 한 레시피</span>
              <img src="@/assets/images/heart_img.jpg" />
            </a>

            <a class="yellow-visible">
              <span>품목설정3</span>
            </a>
          </div>
        </div>

        <!-- big-box -->
        <div class="big-box">
          <div>
            <div class="red-visible right">
              <h3>깊티콘관리</h3>
            </div>

            <!-- <div class="red-visible right red1">
              <h3>기프티콘 등록</h3>
            </div> -->

            <div class="yellow-visible right">
              <h3>관심물품 관리</h3>
            </div>

            <div class="blue-visible right">
              <h1>레시피 관리</h1>
            </div>

            <div class="darkgray-visible right">
              <h1>설정</h1>
              <img src="http://www.savorypixel.com/678799/mountains/k2.jpg" />
            </div>

            <div class="original right">
              <img src="@/assets/images/center_cloud.png" />
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";

export default {
  data() {
    return {
      tempClass: "",
    };
  },
  components: {
    Navbar,
  },
  methods: {
    selec(info) {
      if (this.tempClass !== "") {
        this.tempClass = this.tempClass + info;
        console.log(tempClass);
      }
    },
    filp(value) {
      this.tempClass = "";
      if ($("#boxes").attr("class") !== "") {
        console.log("if", this.tempClass);
        $("#banner #boxes").attr("class", "");
      } else {
        this.tempClass = value + "-chosen";
        console.log(this.tempClass);
      }
    },
  },
};
</script>

<style>
@font-face {
  font-family: "KyoboHand";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/KyoboHand.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
.big-box > div > .original > img {
  width: 100%;
  height: 100%;
}
body {
  min-width: 320px;
  padding: 0;
}
.full {
  width: 100%;
  background: #eee;
}
.topbanner {
  width: 1170px;
  margin: 20px auto;
}
#banner #boxes {
  height: 500px;
  position: relative;
}
#banner #boxes div {
  position: absolute;
}
.small-box {
  -webkit-perspective: 1000;
  perspective: 1000;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  height: 249px;
  width: 249px;
}
.small-box > div {
  position: relative;
  width: 100%;
  height: 100%;
}
.small-box a {
  -webkit-transition-duration: 0.4s;
  transition-duration: 0.4s;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  -webkit-backface-visibility: hidden;
  -moz-backface-visibility: hidden;
  -ms-backface-visibility: hidden;
  backface-visibility: hidden;
  -webkit-perspective: 1000;
  -ms-perspective: 1000;
  -moz-perspective: 1000;
  perspective: 1000;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-repeat: no-repeat;
  background-position: 0 0;
  overflow: hidden;
}
.small-box div:before,
.big-box div:before {
  content: "";
  display: block;
  background: rgba(0, 0, 0, 0);
  z-index: 98;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  -webkit-transition: background 0.2s;
  transition: background 0.2s;
}
.small-box div:hover:before {
  z-index: 98;
  background: rgba(0, 0, 0, 0.3);
  -webkit-transition: background 0.2s;
  transition: background 0.2s;
}
/* .big-box .original:hover:before {
  background: rgba(0, 0, 0, 0);
} */

.original {
  opacity: 0.9999;
}
.small-box a:not(.original) {
  -webkit-transform: rotateY(180deg);
  transform: rotateY(180deg);
}
.small-box > div > a:not(.original):hover {
  cursor: pointer;
}
.small-box > div > a.original:hover {
  cursor: default;
}
.small-box a span,
.big-box > .right > span {
  position: absolute;
  bottom: 0;
  left: 0;
  font-size: 24px;
  color: #fff;
  letter-spacing: 1px;
  z-index: 100;
  background: rgba(0, 0, 0, 0.4);
  width: 100%;
  padding: 10px 20px;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  text-shadow: 0 0 4px rgba(0, 0, 0, 0.8), 0 0 30px rgba(0, 0, 0, 0.8);
}
/* .small-box .original img {
  display: none;
} */
.small-box > div > a > img {
  width: 100%;
}
.small-box .original span {
  height: 100%;
  text-align: center;
  /* line-height: 249px; */
  padding-top: 135px;
  background: none;
  text-shadow: none;
}
/* .big-box div span {
  font-size: 24px;
  font-weight: normal;
} */
.small-box.red {
  top: 0;
  left: 0;
}
.small-box.blue {
  top: 251px;
  left: 0;
}
.small-box.yellow {
  top: 0;
  left: 251px;
}
.small-box.darkgray {
  top: 251px;
  left: 251px;
}
.small-box .original span:before {
  content: "";
  display: block;
  position: absolute;
  height: 50px;
  width: 50px;
  left: 50%;
  margin-left: -25px;
  top: 80px;
}
.small-box.red .original span:before {
  background: url("../assets/icons/gift_removebg.png");
  background-size: cover;
  background-position: center center;
}
.small-box.yellow .original span:before {
  background: url("../assets/icons/star_removebg.png");
  background-size: cover;
  background-position: center center;
}
.small-box.blue .original span:before {
  background: url("../assets/icons/list_removebg.png");
  background-size: cover;
  background-position: center center;
}
.small-box.darkgray .original span:before {
  background: url("../assets/icons/settings_removebg.png");
  background-size: cover;
  background-position: center center;
}
/* spin forward all correct cards (small-box) */
.red-chosen .small-box:not(.red) a.red-visible,
.blue-chosen .small-box:not(.blue) a.blue-visible,
.yellow-chosen .small-box:not(.yellow) a.yellow-visible,
.darkgray-chosen .small-box:not(.darkgray) a.darkgray-visible {
  -webkit-transform: rotateY(360deg) translateZ(1px);
  transform: rotateY(360deg);
}
/* spin backward all non-correct cards (small-box)*/
.red-chosen .small-box:not(.red) a:not(.red-visible),
.blue-chosen .small-box:not(.blue) a:not(.blue-visible),
.yellow-chosen .small-box:not(.yellow) a:not(.yellow-visible),
.darkgray-chosen .small-box:not(.darkgray) a:not(.darkgray-visible) {
  -webkit-transform: rotateY(180deg) translateZ(-2px);
  transform: rotateY(180deg);
}
.red-chosen .small-box:not(.red) a.blue-visible,
.blue-chosen .small-box:not(.blue) a.red-visible,
.yellow-chosen .small-box:not(.yellow) a.red-visible,
.darkgray-chosen .small-box:not(.darkgray) a.red-visible,
.red-chosen .small-box.blue a.red-visible,
.blue-chosen .small-box.red a.blue-visible,
.yellow-chosen .small-box.red a.yellow-visible,
.darkgray-chosen .small-box.red a.darkgray-visible {
  -webkit-transition-delay: 0.1s;
  transition-delay: 0.1s;
}
.red-chosen .small-box:not(.red) a.yellow-visible,
.blue-chosen .small-box:not(.blue) a.yellow-visible,
.yellow-chosen .small-box:not(.yellow) a.blue-visible,
.darkgray-chosen .small-box:not(.darkgray) a.blue-visible,
.red-chosen .small-box.yellow a.red-visible,
.blue-chosen .small-box.yellow a.blue-visible,
.yellow-chosen .small-box.blue a.yellow-visible,
.darkgray-chosen .small-box.blue a.darkgray-visible {
  -webkit-transition-delay: 0.2s;
  transition-delay: 0.2s;
}
.red-chosen .small-box:not(.red) a.darkgray-visible,
.blue-chosen .small-box:not(.blue) a.darkgray-visible,
.yellow-chosen .small-box:not(.yellow) a.darkgray-visible,
.darkgray-chosen .small-box:not(.darkgray) a.yellow-visible,
.red-chosen .small-box.darkgray a.red-visible,
.blue-chosen .small-box.darkgray a.blue-visible,
.yellow-chosen .small-box.darkgray a.yellow-visible,
.darkgray-chosen .small-box.yellow a.darkgray-visible {
  -webkit-transition-delay: 0.3s;
  transition-delay: 0.3s;
}
#boxes .big-box {
  -webkit-perspective: 1000;
  perspective: 1000;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  height: 500px;
  width: 668px;
  right: 0;
}
.big-box > div {
  position: relative;
  width: 100%;
  height: 100%;
}
.big-box > div > .right {
  -webkit-transition-duration: 0.4s;
  transition-duration: 0.4s;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  -webkit-backface-visibility: hidden;
  -moz-backface-visibility: hidden;
  -ms-backface-visibility: hidden;
  backface-visibility: hidden;
  -webkit-perspective: 1000;
  perspective: 1000;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-repeat: no-repeat;
  background-position: 0 0;
  overflow: hidden;
}
.big-box > .right > div.original {
  background-color: #ddd;
  background-size: 100%;
}
.big-box > .right > div:not(.original) {
  -webkit-transform: translate3d(0, 0, 0);
  transform: translate3d(0, 0, 0);
  -webkit-transform: rotateX(180deg);
  transform: rotateX(180deg);
  overflow: hidden;
}
/* spin forward correct card (big-box) */
.red-chosen .big-box .right.red-visible,
.blue-chosen .big-box .right.blue-visible,
.yellow-chosen .big-box .right.yellow-visible,
.darkgray-chosen .big-box .right.darkgray-visible {
  -webkit-transform: rotateX(360deg) translateZ(1px);
  transform: rotateX(360deg);
}
/* spin backward all correct cards (big-box) */
.red-chosen .big-box .right:not(.red-visible),
.blue-chosen .big-box .right:not(.blue-visible),
.yellow-chosen .big-box .right:not(.yellow-visible),
.darkgray-chosen .big-box .right:not(.darkgray-visible) {
  -webkit-transform: rotateX(180deg) translateZ(-1px);
  transform: rotateX(180deg);
}
.red-chosen .big-box .right,
.blue-chosen .big-box .right,
.yellow-chosen .big-box .right,
.darkgray-chosen .big-box .right {
  -webkit-transition-delay: 0.4s;
  transition-delay: 0.4s;
}
/* #ff7473 / #ffc952 / #47b8e0 / #34314c */
.red .original {
  background-color: #ff7473;
}
.blue .original {
  background-color: #47b8e0;
}
.yellow .original {
  background-color: #ffc952;
}
.darkgray .original {
  background-color: #34314c;
}
</style>
