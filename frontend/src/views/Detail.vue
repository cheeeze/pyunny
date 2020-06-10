<template>
  <div>
    <navbar></navbar>
    <div class="detail-container">
      <!-- 상단 제품 정보 -->
      <div class="detail-info">
        <div class="item-photo">
          <img
            class="item-img"
            src="http://gs25appimg.gsretail.com/imgsvr/item/GD_8801115137306_001.jpg"
            alt=""
          />
        </div>
        <div class="item-info">
          <div class="item-convs">
            <img id="item-conv" src="@/assets/icons/gs25.png" alt="" />
            <img id="item-conv" src="@/assets/icons/cu.png" alt="" />
            <img id="item-conv" src="@/assets/icons/emart.jpg" alt="" />
            <img id="item-conv" src="@/assets/icons/seven.png" alt="" />
            <img id="item-conv" src="@/assets/icons/ministop.png" alt="" />
          </div>
          <h1 id="item-title">서울)달고나우유300ML</h1>
          <h2 id="item-price">
            3,000원<span style="margin-left: 15px;"
              ><b-badge variant="info">2 + 1</b-badge></span
            >
          </h2>
          <!-- <h3 id="item-origin-price">
            (1개당 1,500원)<button id="item-btn">
              <img height="40px;" src="@/assets/icons/plus.png" alt="" />
            </button>
          </h3> -->
          <h3 id="item-origin-price">
            (1개당 1,500원)
            <b-button
              id="item-btn"
              @click="addFavorite()"
              v-b-popover.hover.bottomleft="
                '관심 제품에 대한 할인 정보를 가장 먼저 알려드려요 :-)'
              "
              title="관심 제품을 등록해보세요!"
              variant="outline-none"
            >
              <img height="40px;" src="@/assets/icons/plus.png" alt="" />
            </b-button>
          </h3>
        </div>
      </div>
      <!---->
      <!-- 재구매 의향 -->
      <div class="item-like">
        <h2 class="subtitle">재구매 의향</h2>
        <div id="like-btns">
          <button id="item-like-btn" @click="itemLike()">😆있다</button
          ><button id="item-like-btn" @click="itemDislike()">없다😑</button>
        </div>
        <b-progress :value="value" class="mb-3"></b-progress>
      </div>
      <!---->
      <!-- 한줄평 부분 || 채은이를 위한 선물 -->
      <div class="item-comment">
        <h2 class="subtitle">한줄평</h2>
        <input
          id="item-comment"
          type="text"
          placeholder="한줄평을 적어보세요."
        /><button id="comment-btn">입력</button>
        <!-- 한줄평 모음 -->
        <div class="comments">
          <div class="comment">
            <h4 id="nickname">나는야편돌이</h4>
            <div id="comment-box">
              <p id="comment-text">역시 우유는 서울우유죠. 그냥 정-석-</p>
            </div>
            <div class="before-reply" v-if="reply">
              <button id="reply-btn" @click="reply = false">답글 달기</button>
            </div>
            <div class="after-reply" v-if="!reply">
              <img
                src="@/assets/icons/rereply.png"
                alt=""
                height="26px"
                width="28px"
              /><input
                type="text"
                placeholder="답글을 작성해볼까요?"
                style="height:30px; width: 80%"
              /><button id="reply-btn" style="color: #47b8e0">게시</button>
              <button
                id="reply-btn"
                style="color: #ff7473; margin-left: 2%;"
                @click="reply = true"
              >
                취소
              </button>
            </div>
            <!-- 대댓 모음 -->
            <div class="rereply">
              <p>
                <img
                  src="@/assets/icons/rereply.png"
                  alt=""
                  height="26px"
                  width="28px"
                  style="margin-right: 2px;"
                />예?! 우유는 파스퇴르 아닙니까...
              </p>
            </div>
            <!---->
          </div>
        </div>
      </div>
      <!--한줄평 끝-->
      <!-- 제품 레시피 -->
      <div class="item-recipe">
        <h2 class="subtitle" style="font-size: 1.4rem;">
          이 제품을 사용한 레시피가 궁금하다면?
        </h2>
        <a href="" style="font-size: 1.3rem; margin-left:65%;">→ 레시피 검색</a>
      </div>
      <!---->
      <!-- 유사 제품 -->
      <div class="sim-item">
        <h2 class="subtitle">이 제품을 좋아한 사용자가 본 다른 제품</h2>
        <item-card></item-card>
      </div>
      <!---->
    </div>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import ItemCard from "@/components/ItemCard.vue";

export default {
  components: {
    Navbar,
    ItemCard,
  },
  data() {
    return {
      like: 0,
      dislike: 0,
      value: 50,
      max: 100,
      reply: true,
      user: true, // 로그인이 되어 있을 경우 true
      score: 0, // 0인 경우 재구매 의향 선택한 적 없는 경우, 1은 있다, 2는 없다
    };
  },
  methods: {
    addFavorite() {
      // 관심 제품 등록
    },
    itemLike() {
      // 로그인이 안되어 있을 때 로그인 필요하다는 alert
      if (this.user) {
        if (this.score === 0) {
          this.like += 1;
          this.score = 1;
        } else if (this.score === 2) {
          this.like += 1;
          this.score = 1;
          this.dislike -= 1;
        }
      } else {
        alert("로그인이 필요한 기능입니다.");
      }
    },
    itemDislike() {
      if (this.user) {
        if (this.score === 0) {
          this.dislike += 1;
          this.score = 2;
        } else if (this.score === 1) {
          this.dislike += 1;
          this.score = 2;
          this.like -= 1;
        }
      } else {
        alert("로그인이 필요한 기능입니다.");
      }
    },
    clickReply() {
      if (this.reply) {
        this.reply = false;
      }
    },
  },
  watch: {
    like: function() {
      this.value = (this.like / (this.like + this.dislike)) * 100;
    },
    dislike: function() {
      this.value = (this.like / (this.like + this.dislike)) * 100;
    },
  },
};
</script>

<style>
.detail-container {
  display: block;
  margin-top: 55px;
  margin: 55px auto;
  padding: 2rem 3rem;
  height: 100vh;
  width: 80%;
  min-width: 320px;
  max-width: 600px;
  /* background-color: rgba(177, 177, 252, 0.616); */
}

.detail-info {
  display: flex;
  width: 100%;
  justify-content: space-between;
}

.item-photo {
  height: 150px;
  width: 150px;
  border-radius: 10%;
  background-color: rgba(211, 211, 211, 0.842);
  display: flex;
  align-items: center;
  justify-content: center;
}
.item-img {
  height: 140px;
  border-radius: 10%;
}
.item-convs {
  height: 40px;
  overflow: hidden;
  align-items: center;
  justify-content: stretch;
  display: flex;
}

#item-conv {
  height: 100%;
  margin: 3px;
  border-radius: 10%;
}

#item-title {
  font-size: 2.3rem;
}

#item-price {
  font-size: 1.5rem;
}

#item-origin-price {
  font-size: 1.2rem;
}

#item-btn {
  background-color: transparent;
  border-style: none;
  margin-left: 5px;
  outline: none;
}

.item-like {
  margin: 25px 0 35px;
}

#like-btns {
  width: 100%;
  display: flex;
  justify-content: space-between;
  outline: none;
}

#item-like-btn {
  background-color: transparent;
  border-style: none;
  font-size: 1.2rem;
  outline: none;
}

.subtitle {
  font-size: 1.6rem;
  font-weight: bold;
  text-align: left;
}

#item-comment {
  width: 90%;
  background-color: #e9ecef;
  border-radius: 5px;
  padding-left: 10px;
  /* margin-left: 0; */
}
#comment-btn {
  width: 8.5%;
  margin-left: 1.5%;
  height: 34px;
  background-color: #8cdef7d3;
  border-radius: 20px;
  outline: none;
}

.comments {
  margin-top: 15px;
  text-align: left;
  /* border: 1px solid lightgray;
  border-radius: 5px; */
}

.comment {
  margin: 5px;
}

#nickname {
  font-size: 1.05rem;
  font-weight: bold;
  margin-bottom: 5px;
}

#comment-box {
  width: 100%;
  padding: 3px;
  border-radius: 5px;
  background-color: rgb(253, 253, 192);
}

#comment-text {
  margin-left: 5px;
  margin-bottom: 0;
}

#reply-btn {
  background-color: transparent;
  border-style: none;
  margin-right: 5px;
  outline: none;
}

.before-reply {
  text-align: right;
}

.rereply {
  margin: -5px 5px 3px;
  z-index: 3;
}

.item-recipe {
  margin: 30px 0;
}

.sim-item {
}
</style>
