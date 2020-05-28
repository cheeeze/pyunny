<template>
  <div id="full">
    <section id="banner" class="topbanner">
      <div id="boxes" :class="tempClass">
        <!-- red box -->
        <div class="small-box red" rel="red" @click="filp('red')">
          <div>
            <a class="red-visible original">
              <span>깊티콘관리</span>
            </a>

            <a target="_blank" class="blue-visible">
              <span>레시피관리1</span>
              <img src="@/assets/images/bbongimg_circle_ver.jpg" />
            </a>

            <a target="_blank" class="yellow-visible">
              <span>품목설정1</span>
            </a>

            <a target="_blank" class="darkgray-visible">
              <span>설정1</span>
              <img
                src="http://www.savorypixel.com/678799/beaches/maldives.jpg"
              />
            </a>
          </div>
        </div>

        <!-- blue-box -->
        <div class="small-box blue" rel="blue" @click="filp('blue')">
          <div>
            <a class="blue-visible original">
              <span>나만의 레시피 관리</span>
            </a>

            <a target="_blank" class="red-visible">
              <span>깊티 관리2</span>
            </a>

            <a target="_blank" class="yellow-visible">
              <span>품목설정2</span>
            </a>

            <a target="_blank" class="darkgray-visible">
              <span>설정3</span>
            </a>
          </div>
        </div>

        <!-- yellow-box -->
        <div class="small-box yellow" rel="yellow" @click="filp('yellow')">
          <div>
            <a class="yellow-visible original">
              <span>관심품목</span>
            </a>

            <a target="_blank" class="red-visible">
              <span>깊티 관리1</span>
            </a>

            <a target="_blank" class="blue-visible">
              <span>레시피관리2</span>
            </a>

            <a target="_blank" class="darkgray-visible">
              <span>설정2</span>
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

            <a target="_blank" class="red-visible">
              <span>깊티 관리3</span>
            </a>

            <a target="_blank" class="blue-visible">
              <span>레시피관리3</span>
            </a>

            <a target="_blank" class="yellow-visible">
              <span>품목설정3</span>
            </a>
          </div>
        </div>

        <!-- big-box -->
        <div class="big-box">
          <div class="right">
            <div v-if="type == 1" target="_blank" class="red-visible">
              <span>기프티콘 관리 page</span>
            </div>

            <div v-else-if="type == 2" target="_blank" class="yellow-visible">
              <span>품목 관리 Page</span>
            </div>

            <div v-else-if="type == 3" target="_blank" class="blue-visible">
              <span>레시피 관리 page</span>
            </div>

            <div v-else-if="type == 4" target="_blank" class="darkgray-visible">
              <span>settings</span>
            </div>

            <div v-else class="original">
              <img src="@/assets/images/bbongimg.jpg" />
            </div>
          </div>
        </div>
      </div>
      <!-- 
      <div v-if="type == 1">red</div>
      <div v-else-if="type == 2">yellow</div>
      <div v-else-if="type == 3">blue</div>
      <div v-else-if="type == 4">darkgray</div>
      <div v-else>default</div> -->
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tempClass: "",
      type: "",
    };
  },
  methods: {
    filp(value) {
      this.tempClass = "";

      if (this.type != 0) {
        this.type = "0";
      } else {
        if (value == "red") {
          this.type = 1;
        }
        if (value == "yellow") {
          this.type = 2;
        }
        if (value == "blue") {
          this.type = 3;
        }
        if (value == "darkgray") {
          this.type = 4;
        }
      }
      if ($("#boxes").attr("class") !== "") {
        $("#banner #boxes").attr("class", "");
      } else {
        this.tempClass = value + "-chosen";
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

body {
  font-family: "KyoboHand";
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
.small-box div:hover:before,
.big-box div:hover:before {
  z-index: 98;
  background: rgba(0, 0, 0, 0.3);
  -webkit-transition: background 0.2s;
  transition: background 0.2s;
}
.big-box .original:hover:before {
  background: rgba(0, 0, 0, 0);
}

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
.big-box .right span {
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
.small-box .original img {
  display: none;
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
  width: 65px;
  left: 50%;
  margin-left: -32px;
  top: 80px;
}
.small-box.red .original span:before {
  background: url("https://upload.wikimedia.org/wikipedia/commons/5/5d/UPC-A-036000291452.png");
}
.small-box.yellow .original span:before {
  background: url("http://www.savorypixel.com/678799/icon_mountains.svg");
}
.small-box.blue .original span:before {
  background: url("http://www.savorypixel.com/678799/icon_mountains.svg");
}
.small-box.darkgray .original span:before {
  background: url("http://www.savorypixel.com/678799/icon_mountains.svg");
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
.big-box > div > div {
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
}
.big-box > .right > div:not(.original) {
  -webkit-transform: translate3d(0, 0, 0);
  transform: translate3d(0, 0, 0);
  -webkit-transform: rotateX(180deg);
  transform: rotateX(180deg);
  overflow: hidden;
}
/* spin forward correct card (big-box) */
.red-chosen .big-box div.red-visible,
.blue-chosen .big-box div.blue-visible,
.yellow-chosen .big-box div.yellow-visible,
.darkgray-chosen .big-box div.darkgray-visible {
  -webkit-transform: rotateX(360deg) translateZ(1px);
  transform: rotateX(360deg);
}
/* spin backward all correct cards (big-box) */
.red-chosen .big-box div:not(.red-visible),
.blue-chosen .big-box div:not(.blue-visible),
.yellow-chosen .big-box div:not(.yellow-visible),
.darkgray-chosen .big-box div:not(.darkgray-visible) {
  -webkit-transform: rotateX(180deg) translateZ(-1px);
  transform: rotateX(180deg);
}
.red-chosen .big-box div,
.blue-chosen .big-box div,
.yellow-chosen .big-box div,
.darkgray-chosen .big-box div {
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
