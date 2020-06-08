<template>
  <div>
    <navbar></navbar>
    <div style="margin-top:100px;">
      <div>
        <!-- <div class="avatar-preview">
          <div
            class="c2"
            id="imagePreview"
            v-bind:style="{'background-image': 'url('+ review.profileImg +')'}"
            style="float:left;"
          ></div>
        </div>-->
        <h1>{{recipe.title}}</h1>
        <h2>필요재료: {{recipe.ingredient}}</h2>

        <div style="float:right;">
          <p>작성자: {{user.nickname}}</p>
          <p>{{recipe.date.substring(0,10)}}</p>
        </div>
      </div>
    </div>

    <div class="text-center" style="clear:both;">
      <p>
        <small>사용한 편의점 상품</small>
      </p>
      <v-chip
        class="ma-2"
        color="orange"
        text-color="white"
        v-for="product in products"
        :key="product.key"
        @click="gotoProductDetail(product.key)"
      >{{product.text}}</v-chip>
    </div>

    <div style="margin-top:50px;">
      <editor-content id="tiptaparea" class="editor__content" v-html="recipe.content" />
    </div>

    <!-- 한줄평 부분 || 채은이를 위한 선물 -->
    <div>
      <div class="item-comment" style="margin-top:50px;">
        <h2 class="subtitle">댓글</h2>
        <input id="item-comment" type="text" placeholder="댓글을 적어보세요." v-model="text" />
        <button id="comment-btn" @click="addComment(0,0)">입력</button>
        <!-- 한줄평 모음 -->
        <div class="comments">
          <div class="comment" v-for="(reply, index) in replys" :key="index">
            <h4 id="nickname">{{reply.nickname}}</h4>
            <div id="comment-box" @click="reply.isreply = !reply.isreply">
              <p id="comment-text">{{reply.content}}</p>
            </div>
            <div class="before-reply" v-if="!reply.isreply">
              <button id="reply-btn" @click="reply.isreply = !reply.isreply">답글 달기</button>
            </div>
            <div class="after-reply" v-if="reply.isreply">
              <img src="@/assets/icons/rereply.png" alt height="26px" width="28px" />
              <input
                type="text"
                placeholder="답글을 작성해볼까요?"
                v-model="childtext"
                style="height:30px; width: 80%"
              />
              <button id="reply-btn" style="color: #47b8e0" @click="addComment(index, reply.id)">게시</button>
              <button
                id="reply-btn"
                style="color: #ff7473; margin-left: 2%;"
                @click="reply.isreply = !reply.isreply;"
              >취소</button>
            </div>
            <!-- 대댓 모음 -->
            <div class="rereply" v-for="(childreply, index) in reply.childComment" :key="index">
              <!-- <p>
              <small>{{childreply.nickname}}</small>
              </p>-->
              <p>
                <img
                  src="@/assets/icons/rereply.png"
                  alt
                  height="26px"
                  width="28px"
                  style="margin-right: 2px;"
                />
                {{childreply.content}}
              </p>
            </div>
            <!---->
          </div>
        </div>
      </div>
    </div>
    <!--한줄평 끝-->
  </div>
</template>
<script>
import Navbar from "@/components/Navbar.vue";
import Axios from "@/api/Recipeaxios";
import UserAxios from "@/api/Useraxios";
import { Editor, EditorContent } from "tiptap";
/* import {
  Blockquote,
  CodeBlock,
  HardBreak,
  Heading,
  HorizontalRule,
  OrderedList,
  BulletList,
  ListItem,
  TodoItem,
  TodoList,
  Bold,
  Code,
  Italic,
  Link,
  Strike,
  Underline,
  History
} from "tiptap-extensions"; */

export default {
  components: {
    EditorContent,
    Navbar
  },
  props: [
    //param: { type: Object },
    "id"
  ],
  data() {
    return {
      recipe: {},
      user: {},
      products: [],
      //reply: true,
      replys: [],
      text: "",
      childtext: "",
      editor: new Editor()
    };
  },
  mounted() {
    this.getRecipe();
  },
  methods: {
    getRecipe() {
      Axios.getRecipeById(
        this.id,
        res => {
          console.log(res);

          this.recipe = res.data;
          this.getUser(res.data.userId);
          this.getProduct(res.data.id);
          this.getComment(res.data.id);
        },
        err => {
          console.log(err);
        }
      );
    },
    getUser(id) {
      UserAxios.mypage(
        id,
        res => {
          this.user = res.data;
        },
        err => {
          console.log(err);
        }
      );
    },
    getProduct(id) {
      Axios.getIngredientProduct(
        id,
        res => {
          this.products = [];
          //console.log(res.data);
          res.data.forEach(element => {
            this.products.push({ text: element.name, key: element.id });
          });
        },
        err => {
          console.log(err);
        }
      );
    },
    gotoProductDetail(id) {
      this.$router.push("/detail/" + id);
    },
    getComment(id) {
      Axios.getComment(
        id,
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
    addComment(index, parentId) {
      console.log("index:" + index + " parentId:" + parentId);
      let data = {
        userId: 1,
        recipeId: this.recipe.id,
        //content: this.text,
        parentId: parentId
      };
      if (parentId == 0) {
        data.content = this.text;
      } else {
        data.content = this.childtext;
      }
      Axios.insertRecipeComment(data, res => {
        console.log(res.data);
        res.data.nickname = this.user.nickname;
        if (parentId == 0) {
          this.replys.push(res.data);
        } else {
          this.replys[index].childComment.push(res.data);
        }
        this.text = "";
        this.childtext = "";
      });
    }
  }
};
</script>
<style scoped>
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
</style>