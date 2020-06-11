<template>
  <div>
    <navbar></navbar>
    <div class="detail-container">
      <!-- 상단 제품 정보 -->
      <div class="detail-info">
        <div class="item-photo">
          <img class="item-img" :src="product.image" alt />
        </div>
        <div class="item-info">
          <div class="item-convs">
            <img
              id="item-conv"
              v-if="product.franchiseId == 646"
              src="@/assets/icons/gs25.png"
              alt
            />
            <img
              id="item-conv"
              v-if="product.franchiseId == 682"
              src="@/assets/icons/cu.png"
              alt
            />
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
            {{ addComma(product.price) }}원
            <span style="margin-left: 15px;">
              <b-badge v-if="product.category.includes('1＋1')" variant="info"
                >1 + 1</b-badge
              >
              <b-badge v-if="product.category.includes('2＋1')" variant="info"
                >2 + 1</b-badge
              >
            </span>
          </h2>
          <!-- <h3 id="item-origin-price">
            (1개당 1,500원)<button id="item-btn">
              <img height="40px;" src="@/assets/icons/plus.png" alt="" />
            </button>
          </h3>-->
          <h3 id="item-origin-price">
            (1개당 {{ addComma(product.price) }}원)
            <b-button
              id="item-btn"
              @click="addFavorite()"
              v-b-popover.hover.bottomleft="
                '관심 제품에 대한 할인 정보를 가장 먼저 알려드려요 :-)'
              "
              title="관심 제품을 등록해보세요!"
              variant="outline-none"
            >
              <img height="40px;" src="@/assets/icons/plus.png" alt />
            </b-button>
          </h3>
        </div>
      </div>
      <!---->
      <!-- 재구매 의향 -->
      <div class="item-like">
        <h2 class="subtitle">재구매 의향</h2>
        <div id="like-btns">
          <button id="item-like-btn" @click="itemLike()">😆있다</button>
          <button id="item-like-btn" @click="itemDislike()">없다😑</button>
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
        <button id="comment-btn" @click="addComment()">
          입력
        </button>
        <!-- 한줄평 모음 -->
        <div class="comments">
          <div class="comment" v-for="(reply, index) in replys" :key="index">
            <h4 id="nickname">{{ reply.nickname }}</h4>
            <div id="comment-box" @click="reply.isreply = !reply.isreply">
              <p id="comment-text">{{ reply.content }}</p>
            </div>
            <div class="comment-delete" v-if="reply.isreply">
              <button id="delete-btn" @click="deleteComment(index, reply.id)">
                댓글 삭제
              </button>
            </div>
          </div>
        </div>
      </div>
      <!--한줄평 끝-->
      <!-- 제품 레시피 -->
      <div class="item-recipe">
        <h2 class="subtitle" style="font-size: 1.4rem;">
          이 제품을 사용한 레시피가 궁금하다면?
        </h2>
        <a href style="font-size: 1.3rem; margin-left:65%;">→ 레시피 검색</a>
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
import Axios from "@/api/Productaxios.js";

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
      replys: [],
      comment: "",
      user: true, // 로그인이 되어 있을 경우 true
      score: parseInt(localStorage.getItem("store")), // 0인 경우 재구매 의향 선택한 적 없는 경우, 1은 있다, 2는 없다

      product: {
        category: "",
        franchiseId: 0,
        id: 0,
        image: "",
        name: "",
        price: 0,
      },

      replyResult:{
        content:"",
        productId:0,
        userId:1,
        nickname:"",
      }
    };
  },
  methods: {
    addFavorite() {
      // 관심 제품 등록
      console.log("hello");
      Axios.insertFavorite(
        {
          productId: this.$route.params.id,
          userId: 1,
        },
        (res) => {
          res;
          alert("관심 상품이 등록되었습니다!");
        },
        (err) => {
          console.log(err);
        }
      );
    },
    itemLike() {
      // 로그인이 안되어 있을 때 로그인 필요하다는 alert
      if (this.user) {
        if (this.score === 0) {
          localStorage.setItem("score",1);
          this.like += 1;
          this.score = 1;
          Axios.insertRating(
            {
              userId:this.userId,
              productId:this.productId,
              score: 1,
            },
            (res)=>{
              console.log(res);
            },
            (err)=>{
              console.log(err);
            }
          );
        } else if (this.score === 2) {
          localStorage.setItem("score",1);
          this.like += 1;
          this.score = 1;
          this.dislike -= 1;
          
          Axios.deleteRating(
            {
              userId:this.userId,
              productId:this.productId,
            },
            (res)=>{
              console.log(res);
              Axios.insertRating(
                {
                  userId:this.userId,
                  productId:this.productId,
                  score: 1,
                },
                (res)=>{
                  console.log(res);
                },
                (err)=>{
                  console.log(err);
                }
              );
            },
            (err)=>{
              console.log(err);
            }
          );
          
        }
      } else {
        alert("로그인이 필요한 기능입니다.");
      }
      console.log(this.score);
    },
    itemDislike() {
      if (this.user) {
        if (this.score === 0) {
          localStorage.setItem("score",2);
          this.dislike += 1;
          this.score = 2;
          Axios.insertRating(
            {
              userId:this.userId,
              productId:this.productId,
              score: 2,
            },
            (res)=>{
              console.log(res);
            },
            (err)=>{
              console.log(err);
            }
          );
        } else if (this.score === 1) {
          localStorage.setItem("score",2);
          this.dislike += 1;
          this.score = 2;
          this.like -= 1;
          

          Axios.deleteRating(
            {
              userId:this.userId,
              productId:this.productId,
            },
            (res)=>{
              console.log(res);
              Axios.insertRating(
                {
                  userId:this.userId,
                  productId:this.productId,
                  score: 2,
                },
                (res)=>{
                  console.log(res);
                },
                (err)=>{
                  console.log(err);
                }
              );
            },
            (err)=>{
              console.log(err);
            }
          );
          
        }
      } else {
        alert("로그인이 필요한 기능입니다.");
      }
    },
    // clickReply() {
    //   if (this.reply) {
    //     this.reply = false;
    //   }
    // },
    addComment() {
      Axios.insertComment(
        {
          content: this.comment,
          productId: this.product.id,
          userId: 1,
        },
        (res) => {
          console.log(res);
          alert("한줄평이 정상적으로 등록되었습니다!");
          this.replyResult = {
            content:res.data.content,
            productId:res.data.productId,
            userId:res.data.userId,
            nickname:this.nickname,
          };
          // res.data.nickname = this.nickname;
          console.log(this.replyResult);
          this.replys.push(this.replyResult);
          Axios.getCommentById(
            this.$route.params.id,
            (res) => {
              this.replys = [];
              res.data.forEach((element) => {
                element.isreply = false;
                this.replys.push(element);
              });
              console.log("replys", res);
            },
            (err) => {
              console.log(err);
            }
          );
        },
        (err) => {
          console.log(err);
        }
      ),
        (this.comment = "");
    },
    deleteComment(idx, id) {
      Axios.deleteComment(
        id,
        (res) => {
          res;
          alert("한줄평이 정상적으로 삭제되었습니다.");

          if (idx > -1) {
            this.replys.splice(idx, 1);
          }
        },
        (err) => {
          console.log(err);
        }
      );
    },
    addComma(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    getProductById(){
      Axios.getCommentById(
        this.$route.params.id,
        (res) => {
          this.replys = [];
          res.data.forEach((element) => {
            element.isreply = false;
            this.replys.push(element);
          });
          console.log("replys", res);
        },
        (err) => {
          console.log(err);
        }
      );
    }
  },
  watch: {
    like: function() {
      this.value = (this.like / (this.like + this.dislike)) * 100;
    },
    dislike: function() {
      this.value = (this.like / (this.like + this.dislike)) * 100;
    },
  },
  mounted() {
    localStorage.clear();
    this.userId=1;
    this.productId=this.$route.params.id;
    if(localStorage.getItem("score") == null){
      localStorage.setItem("score",0);
    }
    this.score=parseInt(localStorage.getItem("score"));

    Axios.getRating(
      this.productId,
      (res) => {
        console.log(res);
        this.like = res.data.inlike;
        this.dislike = res.data.dislike;
      },
      (err) => {
        console.log(err);
      }
    )
    console.log(this.score);
    Axios.getProductById(
      this.$route.params.id,
      (res) => {
        this.product = res.data;
        console.log(res);
      },
      (err) => {
        console.log(err);
      }
    ),
    
      Axios.getCommentById(
        this.$route.params.id,
        (res) => {
          this.replys = [];
          res.data.forEach((element) => {
            element.isreply = false;
            this.replys.push(element);
          });
          console.log("replys", res);
        },
        (err) => {
          console.log(err);
        }
      );
    //   http
    //     .get("/api/product/" + this.$route.params.id)
    //     .then((res) => {
    //       this.product = res.data;
    //       console.log(this.product);
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //     });
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

.sim-item {
}
</style>
