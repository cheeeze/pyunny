<template>
  <div id="full">
    <navbar></navbar>
    <section id="banner" class="topbanner">
      <div id="boxes" :class="tempClass" style="display:flex; flex-direction: column;">
        <!-- red box -->
        <!-- <div style="flex: 1 1 0;"> -->
        <div class="small-box red" @click="filp('red')">
          <div>
            <div class="red-visible original left">
              <span>기프티콘 관리</span>
            </div>

            <div class="blue-visible left" style="z-index:100;" @click="selec('blue1')">
              <span>내가 쓴 레시피</span>
              <img src="@/assets/images/write_img.jpg" />
            </div>

            <div class="yellow-visible left" @click="selec('yellow1')">
              <span>서비스 준비중</span>
              <img src="@/assets/images/red_img.jpg" />
            </div>

            <div class="darkgray-visible left" style="z-index:100;" @click="selec('darkgray1')">
              <span>내 정보 수정</span>
              <img src="@/assets/images/person_img.jpg" />
            </div>
          </div>
        </div>

        <!-- yellow-box -->
        <div class="small-box yellow" @click="filp('yellow')">
          <div>
            <div class="yellow-visible original left">
              <span>관심품목</span>
            </div>

            <div class="red-visible left" style="z-index:100;" @click="selec('red1')">
              <span>기프티콘 등록</span>
              <img src="@/assets/images/plus_img.jpg" />
            </div>

            <div class="blue-visible left" style="z-index:100;" @click="selec('blue2')">
              <span>내가 쓴 댓글</span>
              <img src="@/assets/images/comment_img.jpg" />
            </div>

            <div class="darkgray-visible left" style="z-index:100;" @click="selec('darkgray2')">
              <span>통신사 멤버십 관리</span>
              <img src="@/assets/images/card_img.jpg" />
            </div>
          </div>
        </div>

        <!-- blue-box -->
        <div class="small-box blue" @click="filp('blue')">
          <div>
            <div class="blue-visible original left">
              <span>나만의 레시피 관리</span>
            </div>

            <div class="red-visible left" style="z-index:100;" @click="selec('red2')">
              <span>내가 가진 기프티콘</span>
              <img src="@/assets/images/more_img.jpg" />
            </div>

            <div class="yellow-visible left" @click="selec('yellow2')">
              <span>서비스 준비중</span>
              <img src="@/assets/images/blue_img.jpg" />
            </div>

            <div class="darkgray-visible left" style="z-index:100;" @click="selec('darkgray3')">
              <span>편의점 적립 관리</span>
              <img src="@/assets/images/save_img.jpg" />
            </div>
          </div>
        </div>

        <!-- black-box -->
        <div class="small-box darkgray" @click="filp('darkgray')">
          <div>
            <div class="darkgray-visible original left">
              <span>설정</span>
            </div>

            <div class="red-visible left" style="z-index:100;" @click="selec('red3')">
              <span>사용한 기프티콘</span>
              <img src="@/assets/images/check_img.jpg" />
            </div>

            <div class="blue-visible left" style="z-index:100;" @click="selec('blue3')">
              <span>좋아요 한 레시피</span>
              <img src="@/assets/images/heart_img.jpg" />
            </div>

            <div class="yellow-visible left" @click="selec('yellow3')">
              <span>서비스 준비중</span>
              <img src="@/assets/images/darkgray_img.jpg" />
            </div>
          </div>
        </div>

        <!---->
        <!-- </div> -->

        <!-- big-box -->
        <div class="big-box" style="display: flex; flex: 1 1 0;">
          <div class="whatis">
            <div class="red-visible right">
              <diV v-if="this.select_menu == 'red1'">
                <h3>기프티콘 등록하기</h3>
                <FileUpload></FileUpload>
              </diV>
              <diV v-if="this.select_menu == 'red2'">
                <h3>사용전 기프티콘</h3>
                <ShowUserGift></ShowUserGift>
              </diV>
              <diV v-if="this.select_menu == 'red3'">
                <h3>사용한 기프티콘</h3>
                <ShowUsedGift></ShowUsedGift>
              </diV>
            </div>
            <div class="yellow-visible right" v-if="color == 'yellow'">
              <h3>나의 관심품목</h3>
              <MyFavorite></MyFavorite>
            </div>

            <div class="blue-visible right">
              <diV v-if="this.select_menu == 'blue1'">
                <h3>내가 쓴 레시피</h3>
                <UserPost></UserPost>
              </diV>
              <diV v-if="this.select_menu == 'blue2'">
                <h3>내가 쓴 댓글</h3>
                <UserComment></UserComment>
              </diV>
              <diV v-if="this.select_menu == 'blue3'">
                <h3>좋아요 레시피</h3>
                <UserLikePost></UserLikePost>
              </diV>
            </div>

            <div class="darkgray-visible right">
              <diV v-if="this.select_menu == 'darkgray1'">
                <h3>내 정보 변경하기</h3>
                <ChangeUserInfo></ChangeUserInfo>
              </diV>
              <diV v-if="this.select_menu == 'darkgray2'">
                <h3>통신사 멤버십 등록하기</h3>
                <RegisterMembership></RegisterMembership>
              </diV>
              <diV v-if="this.select_menu == 'darkgray3'">
                <h3>편의점 적립 등록하기</h3>
                <RegisterAccPoint></RegisterAccPoint>
              </diV>
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
import FileUpload from "@/components/FileUpload.vue";
import ShowUserGift from "@/components/ShowUserGift.vue";
import ShowUsedGift from "@/components/ShowUsedGift.vue";
import MyFavorite from "@/components/MyFavorite.vue";
import UserPost from "@/components/UserPost.vue";
import UserComment from "@/components/UserComment.vue";
import UserLikePost from "@/components/UserLikePost.vue";
import ChangeUserInfo from "@/components/ChangeUserInfo.vue";
import RegisterMembership from "@/components/RegisterMembership.vue";
import RegisterAccPoint from "@/components/RegisterAccPoint.vue";

export default {
  data() {
    return {
      tempClass: "",
      color: "",
      select_menu: "",
      favoriteshow: false
    };
  },
  components: {
    Navbar,
    FileUpload,
    ShowUserGift,
    ShowUsedGift,
    MyFavorite,
    UserPost,
    UserComment,
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
      }
    },
    filp(value) {
      if (this.tempClass == "") {
        console.log("start flip", this.tempClass);
        if ($("#boxes").attr("class") !== "") {
          $("#boxes").attr("class", "");
        } else {
          this.tempClass = value + "-chosen";
          this.color = value;
          console.log("flip-else", this.tempClass, this.color);
        }
      } else {
        if (this.tempClass != "" && this.color != "") {
          if (this.color == value) {
            $("#boxes").attr("class", "");
            this.tempClass = "";
            this.color = "";
            this.select_menu = "";
          }
        }
      }
    }
  }
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
  margin-top: 8rem;
}
#banner #boxes {
  height: 500px;
  position: relative;
  z-index: 1;
}
#banner > #boxes > div {
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
.big-box div:before {
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
/* .small-box .original img {
  display: none;
} */
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
  -webkit-perspective: 1000;
  perspective: 1000;
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
  -webkit-perspective: 1000;
  perspective: 1000;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-repeat: no-repeat;
  background-position: 0 0;
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
  z-index: 100;
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
