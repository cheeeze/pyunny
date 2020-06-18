<template>
  <div>
    <navbar></navbar>
    <div class="recipecontainer">
      <div style="margin-top:60px; display: flex; margin-left:20px;">
        <button class="back" @click="niceback" style="float: left;">
          <img src="@/assets/icons/back.png" width="25px;" />
        </button>
      </div>
      <div id="search" class="box_search" style="margin-top:15px;">
        <img
          src="@/assets/icons/x.png/"
          v-show="recommShow"
          @click="recomm"
          style="float: left; width:1.5em; z-index:10;"
        />
        <input
          type="search"
          id="innerQuery"
          class="tf-keyword"
          v-model="keyword"
          v-on:keyup.enter="search"
          @click="recomm"
          title="검색어 입력"
          placeholder="상품 검색"
          maxlength="100"
          style="background-color: #f2f3f5;"
          v-bind:style="{ width: recommShow==false?'95%':'85%'}"
        />
      </div>

      <div>
        <select v-model="selected" style="margin-bottom:20px;margin-right: 40px;">
          <option>인기순</option>
          <option>최신순</option>
        </select>
        <v-btn class="ma-2" tile outlined color="success" @click="recipeCreate">
          <v-icon left>mdi-pencil</v-icon>작성하기
        </v-btn>
      </div>

      <div class="recipe-list-area">
        <v-card class="mx-auto" max-width="500">
          <v-container fluid>
            <v-row dense>
              <v-col
                v-for="card in cards"
                :key="card.title"
                :cols="card.flex"
                @click="gotoDetail(card.id)"
              >
                <v-card>
                  <v-img
                    :src="card.src"
                    class="white--text align-end"
                    gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                    height="200px"
                  >
                    <v-card-title v-text="card.title"></v-card-title>
                  </v-img>

                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <!-- <v-list-item-avatar color="grey darken-3">
                    <v-img
                      class="elevation-6"
                      src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
                    ></v-img>
                    </v-list-item-avatar>-->
                    <v-list-item-content>
                      <v-list-item-title>{{card.date}}</v-list-item-title>
                    </v-list-item-content>
                    <v-btn icon>
                      <v-icon>mdi-heart</v-icon>
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
            </v-row>
          </v-container>
        </v-card>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import Axios from "@/api/Recipeaxios";
export default {
  components: {
    Navbar
  },
  data() {
    return {
      recommShow: false,
      keyword: "",
      selected: "최신순",
      userId: 0,

      cards: [
        /* {
          title: "Pre-fab homes",
          src: "https://cdn.vuetifyjs.com/images/cards/house.jpg",
          flex: 6
        },
        {
          title: "Favorite road trips",
          src: "https://cdn.vuetifyjs.com/images/cards/road.jpg",
          flex: 6
        },
        {
          title: "Best airlines",
          src: "https://cdn.vuetifyjs.com/images/cards/plane.jpg",
          flex: 6
        } */
      ]
    };
  },
  watch: {
    selected: function(v) {
      if (v == "인기순") this.popularityOrder();
      else if (v == "최신순") this.recentOrder();
    }
  },
  mounted() {
    this.recentOrder();

    if (sessionStorage.getItem("user") != null) {
      this.userId = JSON.parse(sessionStorage.getItem("user"));
    }
  },
  methods: {
    recomm() {
      if (!this.recommShow) this.recommShow = !this.recommShow;
      else {
        this.recommShow = !this.recommShow;
        this.keyword = "";
      }
    },
    recipeCreate() {
      if (this.userId == 0) {
        return alert("로그인 후 이용가능합니다.");
      }
      this.$router.push("/recipecreate/");
    },
    popularityOrder() {
      Axios.getRecipePopularOrdered(
        res => {
          this.cards = [];
          //console.log(res.data);
          res.data.forEach(element => {
            let src = require("@/assets/icons/defaultrecipe.png");
            let start = element.content.indexOf("http://", 1);
            if (start > 0) {
              let last = element.content.indexOf('"', start);
              src = element.content.substring(start, last);
            }
            //console.log(src);

            this.cards.push({
              id: element.id,
              userId: element.userId,
              date: element.date,
              title: element.title,
              src: src,
              flex: 6
            });
          });
        },
        err => {
          console.log(err);
        }
      );
    },
    recentOrder() {
      Axios.getRecipeRecentOrdered(
        res => {
          this.cards = [];
          //console.log(res.data);
          res.data.forEach(element => {
            let src = require("@/assets/icons/defaultrecipe.png");
            let start = element.content.indexOf("http://", 1);
            if (start > 0) {
              let last = element.content.indexOf('"', start);
              src = element.content.substring(start, last);
            }
            //console.log(src);

            this.cards.push({
              id: element.id,
              userId: element.userId,
              date: element.date,
              title: element.title,
              src: src,
              flex: 6
            });
          });
        },
        err => {
          console.log(err);
        }
      );
    },
    gotoDetail(id) {
      this.$router.push("/recipedetail/" + id);
    },
    search() {
      Axios.getRecipeBySearch(
        this.keyword,
        res => {
          this.cards = [];
          //console.log(res.data);
          res.data.forEach(element => {
            let src = require("@/assets/icons/defaultrecipe.png");
            let start = element.content.indexOf("http://", 1);
            if (start > 0) {
              let last = element.content.indexOf('"', start);
              src = element.content.substring(start, last);
            }
            //console.log(src);

            this.cards.push({
              id: element.id,
              userId: element.userId,
              date: element.date,
              title: element.title,
              src: src,
              flex: 6
            });
          });
          this.keyword = "";
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

<style scoped>
.box_search {
  overflow: hidden;
  position: relative;
  height: 40px;
  margin: 2px 8px 0;
  padding: 6px 10px 1px;
  border-radius: 5px;
  background-color: #f2f3f5;
  margin-bottom: 10px;
}

select {
  -webkit-appearance: auto;
}

.recipecontainer {
  padding: 1.5em 20vw;
}

@media only screen and (max-width: 430px) {
  .recipecontainer {
    padding: 1.5em 5vw;
  }
}
</style>