<template>
  <div>
    <navbar></navbar>
    <div class="detail-container">
      <!-- 상단 제품 정보 -->
      <div style="display: flex; margin-left:20px; margin-bottom:20px;">
        <button class="back" @click="niceback" style="float: left;">
          <img src="@/assets/icons/back.png" width="25px;" />
        </button>
      </div>
      <div class="detail-info">
        <div class="item-photo">
          <img class="item-img" :src="product.image" alt />
        </div>
        <div class="item-info">
          <div class="item-convs">
            <img id="item-conv" v-if="product.franchiseId == 646" src="@/assets/icons/gs25.png" alt />
            <img id="item-conv" v-if="product.franchiseId == 682" src="@/assets/icons/cu.png" alt />
            <img
              id="item-conv"
              v-if="product.franchiseId == 936"
              src="@/assets/icons/emart.jpg"
              alt
            />
            <img
              id="item-conv"
              v-if="product.franchiseId == 970"
              src="@/assets/icons/seven.png"
              alt
            />
            <img
              id="item-conv"
              v-if="product.franchiseId == 756"
              src="@/assets/icons/ministop.png"
              alt
            />
          </div>
          <h1 id="item-title">{{ product.name }}</h1>
          <h2 id="item-price">
            {{ product.price }}원
          <h3 id="item-origin-price">
            (1개당 {{ product.price }}원)
            <b-button
              id="item-btn"
              @click="addFavorite"
              v-b-popover.hover.bottomleft="
                '관심 제품에 대한 할인 정보를 가장 먼저 알려드려요 :-)'
              "
              title="관심 제품을 등록해보세요!"
              variant="outline-none"
            >
              <img
                height="40px;"
                src="@/assets/icons/plus.png"
                style="border: 3px solid; border-radius: 50%;"
                alt
              />
            </b-button>
          </h3>
        </div>
      </div>
      <!---->
      <!-- 재구매 의향 -->
      <div class="item-like">
        <h2 class="subtitle">재구매 의향</h2>
        <div id="like-btns">
          <button id="item-like-btn" @click="itemLike">😆있다({{like}})</button>
          <button id="item-like-btn" @click="itemDislike">없다😑({{dislike}})</button>
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
          v-model="comment"
          @keyup.enter="addComment()"
        />
        <button id="comment-btn" @click="addComment()">입력</button>
        <!-- 한줄평 모음 -->
        <div class="comments">
          <div class="comment" v-for="(reply, index) in replys" :key="index">
            <h4 id="nickname">{{ reply.nickname }}</h4>
            <div id="comment-box" @click="reply.isreply = !reply.isreply">
              <p id="comment-text">{{ reply.content }}</p>
            </div>
            <div class="comment-delete" v-if="reply.isreply">
              <button id="delete-btn" @click="deleteComment(index, reply.id)">댓글 삭제</button>
            </div>
          </div>
        </div>
      </div>
      <!--한줄평 끝-->

      <!-- 유사 제품 -->
      <div class="sim-item" style="margin-top:20px;">
        <h2 class="subtitle">이런건 어떠세요?</h2>
        <item-card :items="items"></item-card>
      </div>
      <!---->
      <!-- 제품 레시피 -->
      <div class="item-recipe">
        <h2 class="subtitle" style="font-size: 1.4rem;">이 제품을 사용한 레시피가 궁금하다면?</h2>
        <sale-card :items="recipes"></sale-card>
      </div>
      <!---->
    </div>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import ItemCard from "@/components/ItemCard.vue";
import SaleCard from "@/components/SaleCard.vue";
import Axios from "@/api/Productaxios.js";
import UserAxios from "@/api/Useraxios.js";

export default {
  components: {
    Navbar,
    ItemCard,
    SaleCard
  },
  props: [
    "id"
  ],
  data() {
    return {
      like: 0,
      dislike: 0,
      value: 0,
      max: 0,
      replys: [],
      comment: "",
      user: {},
      product: {},
      replyResult: {},
      userId: 0,
      items: [],
      recipes: []
    };
  },
  mounted() {
    if (sessionStorage.getItem("user") != null) {
      this.userId = JSON.parse(sessionStorage.getItem("user"));

      UserAxios.mypage(
        this.userId,
        res => {
          this.user = res.data;
        },
        err => {
          console.log(err);
        }
      );
    }

    Axios.getRating(
      this.id,
      res => {
        console.log(res);
        this.like = res.data.inlike;
        this.dislike = res.data.dislike;

        this.value = (this.like / (this.like + this.dislike)) * 100;
      },
      err => {
        console.log(err);
      }
    );

    Axios.getProductById(
      this.id,
      res => {
        this.product = res.data;
        this.product.price = this.product.price
          .toString()
          .replace(/\B(?=(\d{3})+(?!\d))/g, ",");

        if (this.product.image == null) {
          this.product.image = require("@/assets/icons/defaultproduct.png");
        }

        this.getSimilarProduct();
        this.getUsedRecipe();
      },
      err => {
        console.log(err);
      }
    );

    Axios.getCommentById(
      this.id,
      res => {
        this.replys = [];
        res.data.forEach(element => {
          element.isreply = false;
          this.replys.push(element);
        });
      },
      err => {
        console.log(err);
      }
    );
  },
  methods: {
    getSimilarProduct() {
      Axios.getSimilarProduct(
        this.product,
        res => {
          this.items = [];
          res.data.forEach(element => {
            element.product = {
              id: element.id,
              name: element.name,
              image: element.image,
              price: element.price
            };
            this.items.push(element);
          });
        },
        err => {
          console.log(err);
        }
      );
    },
    getUsedRecipe() {
      Axios.getUsedRecipe(
        this.id,
        res => {
          this.recipes = [];
          res.data.forEach(element => {
            element.date = element.date.substring(0, 10);

            this.recipes.push(element);
          });
        },
        err => {
          console.log(err);
        }
      );
    },
    addFavorite() {
      // 관심 제품 등록

      if (sessionStorage.getItem("user") != null) {
        this.userId = JSON.parse(sessionStorage.getItem("user"));
      }
      if (this.userId == 0) {
        return alert("로그인 후 이용가능합니다.");
      }

      Axios.insertFavorite(
        {
          productId: this.id,
          userId: this.userId
        },
        res => {
          res;
          alert("관심 상품이 등록되었습니다!");
        },
        err => {
          console.log(err);
        }
      );
    },
    itemLike() {
      if (sessionStorage.getItem("user") != null) {
        this.userId = JSON.parse(sessionStorage.getItem("user"));
      }
      if (this.userId == 0) {
        return alert("로그인 후 이용가능합니다.");
      }

      let data = {
        userId: this.userId,
        productId: this.id,
        score: 1
      };
      Axios.insertRating(
        data,
        res => {
          res;
          this.like = parseInt(this.like + 1);
        },
        err => {
          console.log(err);
        }
      );
    },
    itemDislike() {
      if (sessionStorage.getItem("user") != null) {
        this.userId = JSON.parse(sessionStorage.getItem("user"));
      }

      if (this.userId == 0) {
        return alert("로그인 후 이용가능합니다.");
      }
      let data = {
        userId: this.userId,
        productId: this.id,
        score: 2
      };
      Axios.insertRating(
        data,
        res => {
          res;
          this.dislike = parseInt(this.dislike + 1);
        },
        err => {
          console.log(err);
        }
      );
    },
    addComment() {
      if (sessionStorage.getItem("user") != null) {
        this.userId = JSON.parse(sessionStorage.getItem("user"));
      }

      if (this.userId == 0) {
        return alert("로그인 후 이용가능합니다.");
      }

      Axios.insertComment(
        {
          content: this.comment,
          productId: this.product.id,
          userId: this.userId
        },
        res => {
          alert("한줄평이 정상적으로 등록되었습니다!");
          this.replyResult = {
            content: res.data.content,
            productId: res.data.productId,
            userId: res.data.userId,
            nickname: this.user.nickname
          };
          this.replys.push(this.replyResult);
          this.comment = "";
        },
        err => {
          console.log(err);
        }
      );
    },
    deleteComment(idx, id) {
      Axios.deleteComment(
        id,
        res => {
          res;
          alert("한줄평이 정상적으로 삭제되었습니다.");

          if (idx > -1) {
            this.replys.splice(idx, 1);
          }
        },
        err => {
          console.log(err);
        }
      );
    },
    addComma(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    getProductById() {
      Axios.getCommentById(
        this.$route.params.id,
        res => {
          this.replys = [];
          res.data.forEach(element => {
            element.isreply = false;
            this.replys.push(element);
          });
        },
        err => {
          console.log(err);
        }
      );
    },

    niceback: function() {
      var numberOfEntries = window.history.length;
      if (numberOfEntries > 2) {
        this.$router.go(-1);
      } else {
        var fpath = this.PageData.backCrumb.url;
        this.$router.push({
          path: fpath
        });
      }
    }
  }
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
}

.comment {
  margin: 0 5px;
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

#delete-btn {
  background-color: transparent;
  border-style: none;
  margin-right: 5px;
  outline: none;
}

.comment-delete {
  text-align: right;
  color: #ff7473;
}

.rereply {
  margin: -5px 5px 3px;
  z-index: 3;
}

.item-recipe {
  margin: 30px 0;
}
</style>
