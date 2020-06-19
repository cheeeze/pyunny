<template>
  <div id="full">
    <navbar></navbar>
    <section id="banner" class="topbanner">
      <div class="btn-container">
        <v-btn id="btn-prev" v-show="responsive && select_menu" @click="goPrev">이전으로</v-btn>
      </div>

      <div id="boxes" :class="tempClass">
        <!-- red box -->

        <div class="small-box red" @click="flip('red')">
          <div>
            <div class="red-visible original left">
              <span>기프티콘 관리</span>
            </div>

            <div class="blue-visible left" @click="selec('blue1')">
              <span>내가 쓴 레시피</span>
              <img src="@/assets/images/write_img.jpg" />
            </div>

            <div class="yellow-visible left" @click="selec('yellow1')">
              <span>관심품목 추가</span>
              <img src="@/assets/images/red_img.jpg" />
            </div>

            <div class="darkgray-visible left" @click="selec('darkgray1')">
              <span>내 정보 수정</span>
              <img src="@/assets/images/person_img.jpg" />
            </div>
          </div>
        </div>

        <!-- yellow-box -->
        <div class="small-box yellow" @click="flip('yellow')">
          <div>
            <div class="yellow-visible original left">
              <span>관심품목</span>
            </div>

            <div class="red-visible left" @click="selec('red1')">
              <span>기프티콘 등록</span>
              <img src="@/assets/images/plus_img.jpg" />
            </div>

            <div class="blue-visible left" @click="selec('blue2')">
              <span>내가 쓴 댓글</span>
              <img src="@/assets/images/comment_img.jpg" />
            </div>

            <div class="darkgray-visible left" @click="selec('darkgray2')">
              <span>통신사 멤버십 관리</span>
              <img src="@/assets/images/card_img.jpg" />
            </div>
          </div>
        </div>

        <!-- blue-box -->
        <div class="small-box blue" @click="flip('blue')">
          <div>
            <div class="blue-visible original left">
              <span>나만의 레시피 관리</span>
            </div>

            <div class="red-visible left" @click="selec('red2')">
              <span>내가 가진 기프티콘</span>
              <img src="@/assets/images/more_img.jpg" />
            </div>

            <div class="yellow-visible left">
              <span>서비스 준비중</span>
              <img src="@/assets/images/blue_img.jpg" />
            </div>

            <div class="darkgray-visible left" @click="selec('darkgray3')">
              <span>편의점 적립 관리</span>
              <img src="@/assets/images/save_img.jpg" />
            </div>
          </div>
        </div>

        <!-- black-box -->
        <div class="small-box darkgray" @click="flip('darkgray')">
          <div>
            <div class="darkgray-visible original left">
              <span>설정</span>
            </div>

            <div class="red-visible left" @click="selec('red3')">
              <span>사용한 기프티콘</span>
              <img src="@/assets/images/check_img.jpg" />
            </div>

            <div class="blue-visible left" @click="selec('blue3')">
              <span>좋아요 한 레시피</span>
              <img src="@/assets/images/heart_img.jpg" />
            </div>

            <div class="yellow-visible left">
              <span>서비스 준비중</span>
              <img src="@/assets/images/darkgray_img.jpg" />
            </div>
          </div>
        </div>

        <!---->
        <!-- </div> -->

        <!-- big-box -->
        <div class="big-box" id="big-box-test">
          <div class="whatis">
            <div class="red-visible right">
              <diV v-show="select_menu == 'red1'">
                <h3>기프티콘 등록하기</h3>
                <FileUpload></FileUpload>
              </diV>
              <diV v-show="select_menu == 'red2'">
                <h3>사용전 기프티콘</h3>
                <ShowUserGift></ShowUserGift>
              </diV>
              <diV v-show="select_menu == 'red3'">
                <h3>사용한 기프티콘</h3>
                <ShowUsedGift></ShowUsedGift>
              </diV>
            </div>
            <div class="yellow-visible right">
              <div v-show="select_menu == 'yellow1'">
                <h3>관심품목을 추가해보세요!</h3>
                <MyFavorite></MyFavorite>
              </div>
            </div>

            <div class="blue-visible right">
              <diV v-show="select_menu == 'blue1'">
                <h3>내가 쓴 레시피</h3>
                <UserPost></UserPost>
              </diV>
              <diV v-show="select_menu == 'blue2'">
                <h3>내가 쓴 댓글</h3>
                <UserComment></UserComment>
              </diV>
              <diV v-show="select_menu == 'blue3'">
                <h3>좋아요 레시피</h3>
                <UserLikePost></UserLikePost>
              </diV>
            </div>

            <div class="darkgray-visible right">
              <diV v-show="select_menu == 'darkgray1'">
                <h3>내 정보 변경하기</h3>
                <ChangeUserInfo></ChangeUserInfo>
              </diV>
              <diV v-show="select_menu == 'darkgray2'">
                <h3>통신사 멤버십 등록하기</h3>
                <RegisterMembership></RegisterMembership>
              </diV>
              <diV v-show="select_menu == 'darkgray3'">
                <h3>편의점 적립 등록하기</h3>
                <RegisterAccPoint></RegisterAccPoint>
              </diV>
            </div>

            <div class="original right" v-show="!responsive">
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
import UserPost from "@/components/UserPost.vue";
import FileUpload from "@/components/FileUpload.vue";
import MyFavorite from "@/components/MyFavorite.vue";
import UserComment from "@/components/UserComment.vue";
import ShowUserGift from "@/components/ShowUserGift.vue";
import ShowUsedGift from "@/components/ShowUsedGift.vue";
import UserLikePost from "@/components/UserLikePost.vue";
import ChangeUserInfo from "@/components/ChangeUserInfo.vue";
import RegisterAccPoint from "@/components/RegisterAccPoint.vue";
import RegisterMembership from "@/components/RegisterMembership.vue";

export default {
  data() {
    return {
      color: "",
      tempClass: "",
      select_menu: "",

      responsive: 0,
      windowWidth: 0
    };
  },
  components: {
    Navbar,
    UserPost,
    FileUpload,
    MyFavorite,
    UserComment,
    ShowUserGift,
    ShowUsedGift,
    UserLikePost,
    ChangeUserInfo,
    RegisterAccPoint,
    RegisterMembership
  },
  methods: {
    selec(menu) {
      if (this.color != "") {
        this.tempClass = this.color + "-chosen";
        this.select_menu = menu;

        if (this.responsive) {
          document.getElementById("boxes").style.visibility = "hidden";
          document.getElementById("big-box-test").style.visibility = "visible";
          document.getElementById("big-box-test").style.position = "unset";
          document.getElementById("big-box-test").style.zIndex = 10;
        }
      }
    },
    flip(value) {
      if (this.tempClass == "") {
        if ($("#boxes").attr("class") !== "") {
          $("#boxes").attr("class", "");
        } else {
          this.tempClass = value + "-chosen";
          this.color = value;
        }
      } else {
        if (this.tempClass != "" && this.color != "") {
          if (this.color == value) {
            $("#boxes").attr("class", "");
            this.color = "";
            this.tempClass = "";
            this.select_menu = "";
          }
        }
      }
    },
    onResize() {
      this.windowWidth = window.innerWidth;
      if (this.windowWidth > 1190) {
        this.responsive = 0;
        banner.style.width = "1170px";
        if (this.select_menu || this.tempClass) {
          document.getElementById("boxes").style.visibility = "visible";
          document.getElementById("big-box-test").style.position = "absolute";
          document.getElementById("big-box-test").style.zIndex = 1;
        }
      } else {
        this.responsive = 1;
        banner.style.width = "500px";
        if (this.select_menu) {
          document.getElementById("boxes").style.visibility = "hidden";
          document.getElementById("big-box-test").style.visibility = "visible";
          document.getElementById("big-box-test").style.position = "unset";
          document.getElementById("big-box-test").style.zIndex = 10;
        }
      }
    },
    goPrev() {
      if (this.select_menu) {
        this.select_menu = "";
        document.getElementById("boxes").style.visibility = "visible";
        document.getElementById("big-box-test").style.position = "absolute";
        document.getElementById("big-box-test").style.zIndex = 1;
      } else if (this.tempClass) {
        this.tempClass = "";
      }
    }
  },
  mounted() {
    window.addEventListener("resize", this.onResize);
    this.windowWidth = window.innerWidth;
    if (this.windowWidth > 1190) {
      this.responsive = 0;
      banner.style.width = "1170px";
    } else {
      this.responsive = 1;
      banner.style.width = "500px";
    }
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.onResize);
  },
  watch: {}
};
</script>

<style scoped>
@font-face {
  font-family: "KyoboHand";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/KyoboHand.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
.btn-container {
  display: flex;
  height: 40px;
  padding: 4px;
}
#btn-prev {
  justify-content: flex-start;
  margin: 2px;
  height: 100%;
}
.big-box > div > .original > img {
  width: 100%;
  height: 100%;
}
.big-box > div > div {
  background: white;
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
  margin: auto;
  margin-top: 8rem;
}
#banner #boxes {
  height: 500px;
  position: relative;
}
#banner > #boxes > div {
  position: absolute;
}
.small-box {
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
.small-box .left {
  -webkit-transition-duration: 0.4s;
  transition-duration: 0.4s;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  -webkit-backface-visibility: hidden;
  -moz-backface-visibility: hidden;
  -ms-backface-visibility: hidden;
  backface-visibility: hidden;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.small-box div:before {
  content: "";
  display: block;
  background: rgba(0, 0, 0, 0);
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  -webkit-transition: background 0.2s;
  transition: background 0.2s;
}

.small-box div:hover:before {
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
.small-box {
  z-index: 5;
}
.small-box .left:not(.original) {
  -webkit-transform: rotateY(180deg);
  transform: rotateY(180deg);
}
.small-box > div > .left:not(.original):hover {
  cursor: pointer;
}
.small-box > div > .left.original:hover {
  cursor: default;
}
.small-box .left span,
.big-box .right span {
  position: absolute;
  bottom: 0;
  left: 0;
  font-size: 24px;
  color: #fff;
  letter-spacing: 1px;
  background: rgba(0, 0, 0, 0.4);
  width: 100%;
  padding: 10px 20px;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  text-shadow: 0 0 4px rgba(0, 0, 0, 0.8), 0 0 30px rgba(0, 0, 0, 0.8);
}
.small-box > div > .left > img {
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
  height: 55px;
  width: 55px;
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
.red-chosen .small-box:not(.red) .left.red-visible,
.blue-chosen .small-box:not(.blue) .left.blue-visible,
.yellow-chosen .small-box:not(.yellow) .left.yellow-visible,
.darkgray-chosen .small-box:not(.darkgray) .left.darkgray-visible {
  -webkit-transform: rotateY(360deg) translateZ(1px);
  transform: rotateY(360deg);
}
/* spin backward all non-correct cards (small-box)*/
.red-chosen .small-box:not(.red) .left:not(.red-visible),
.blue-chosen .small-box:not(.blue) .left:not(.blue-visible),
.yellow-chosen .small-box:not(.yellow) .left:not(.yellow-visible),
.darkgray-chosen .small-box:not(.darkgray) .left:not(.darkgray-visible) {
  -webkit-transform: rotateY(180deg) translateZ(-2px);
  transform: rotateY(180deg);
}
.red-chosen .small-box:not(.red) .left.blue-visible,
.blue-chosen .small-box:not(.blue) .left.red-visible,
.yellow-chosen .small-box:not(.yellow) .left.red-visible,
.darkgray-chosen .small-box:not(.darkgray) .left.red-visible,
.red-chosen .small-box.blue .left.red-visible,
.blue-chosen .small-box.red .left.blue-visible,
.yellow-chosen .small-box.red .left.yellow-visible,
.darkgray-chosen .small-box.red .left.darkgray-visible {
  -webkit-transition-delay: 0.1s;
  transition-delay: 0.1s;
}
.red-chosen .small-box:not(.red) .left.yellow-visible,
.blue-chosen .small-box:not(.blue) .left.yellow-visible,
.yellow-chosen .small-box:not(.yellow) .left.blue-visible,
.darkgray-chosen .small-box:not(.darkgray) .left.blue-visible,
.red-chosen .small-box.yellow .left.red-visible,
.blue-chosen .small-box.yellow .left.blue-visible,
.yellow-chosen .small-box.blue .left.yellow-visible,
.darkgray-chosen .small-box.blue .left.darkgray-visible {
  -webkit-transition-delay: 0.2s;
  transition-delay: 0.2s;
}
.red-chosen .small-box:not(.red) .left.darkgray-visible,
.blue-chosen .small-box:not(.blue) .left.darkgray-visible,
.yellow-chosen .small-box:not(.yellow) .left.darkgray-visible,
.darkgray-chosen .small-box:not(.darkgray) .left.yellow-visible,
.red-chosen .small-box.darkgray .left.red-visible,
.blue-chosen .small-box.darkgray .left.blue-visible,
.yellow-chosen .small-box.darkgray .left.yellow-visible,
.darkgray-chosen .small-box.yellow .left.darkgray-visible {
  -webkit-transition-delay: 0.3s;
  transition-delay: 0.3s;
}
#boxes .big-box {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  height: 500px;
  width: 668px;
  right: 0;
}
.big-box > .whatis {
  /* position: relative; */
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
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  overflow-y: auto;
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
