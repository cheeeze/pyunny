<template>
  <div>
    <navbar></navbar>
    <div style="padding:0.5em;">
      <div style="margin-top:60px; display: flex; margin-left:20px;">
        <button class="back" @click="niceback" style="float: left;">
          <img src="@/assets/icons/back.png" width="25px;" />
        </button>
      </div>
      <div class="text-area" style="margin-top:20px">
        <input
          type="search"
          id="innerQuery"
          class="tf-keyword"
          title="제목 입력"
          v-model="title"
          placeholder="레시피 이름!"
          maxlength="100"
          style="width:95%; background-color: #f2f3f5; border: none;"
        />
      </div>

      <div class="ingredient-area">
        <input
          type="search"
          id="innerQuery"
          class="tf-keyword"
          title="재료 입력"
          v-model="ingredient"
          placeholder="필요한 재료!"
          maxlength="100"
          style="width:95%; background-color: #f2f3f5; border: none;"
        />
      </div>

      <v-container fluid style="box-shadow:none;">
        <v-row>
          <v-col cols="12">
            <v-combobox v-model="select" :items="items" label="편의점 상품" multiple outlined dense></v-combobox>
          </v-col>
        </v-row>
      </v-container>

      <div class="editor c4" style="width:100%;">
        <editor-menu-bar :editor="editor" v-slot="{ commands, isActive }">
          <div class="menubar">
            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.bold() }"
              @click="commands.bold"
            >
              <icon name="bold" id="bold" />
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.italic() }"
              @click="commands.italic"
            >
              <icon name="italic" />
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.strike() }"
              @click="commands.strike"
            >
              <icon name="strike" />
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.underline() }"
              @click="commands.underline"
            >
              <icon name="underline" />
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.code() }"
              @click="commands.code"
            >
              <icon name="code" />
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.paragraph() }"
              @click="commands.paragraph"
            >
              <icon name="paragraph" />
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.heading({ level: 1 }) }"
              @click="commands.heading({ level: 1 })"
            >H1</button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.heading({ level: 2 }) }"
              @click="commands.heading({ level: 2 })"
            >H2</button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.heading({ level: 3 }) }"
              @click="commands.heading({ level: 3 })"
            >H3</button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.bullet_list() }"
              @click="commands.bullet_list"
            >
              <icon name="ul" />
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.ordered_list() }"
              @click="commands.ordered_list"
            >
              <icon name="ol" />
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.blockquote() }"
              @click="commands.blockquote"
            >
              <icon name="quote" />
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.code_block() }"
              @click="commands.code_block"
            >
              <icon name="code" />
            </button>

            <button class="menubar__button" @click="commands.horizontal_rule">
              <icon name="hr" />
            </button>

            <button class="menubar__button" @click="commands.undo">
              <icon name="undo" />
            </button>

            <button class="menubar__button" @click="commands.redo">
              <icon name="redo" />
            </button>

            <button class="menubar__button" @click="showImagePrompt(commands.image)">
              <icon name="image" />
            </button>

            <div>
              <input
                ref="image"
                class="file-upload-input"
                type="file"
                style="display:none;"
                @change="readURL"
                accept="image/*"
              />
            </div>
          </div>
        </editor-menu-bar>

        <div style="margin-top:30px;">
          <editor-content id="tiptaparea" class="editor__content" :editor="editor" />
        </div>

        <div style="margin-top:30px;">
          <v-btn text color="primary" @click="preprocessing">등록!</v-btn>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "@/assets/styles/editor/editor.scss";
import "@/assets/styles/editor/main.scss";
import "@/assets/styles/editor/menubar.scss";
import "@/assets/styles/editor/menububble.scss";
import "@/assets/styles/editor/variables.scss";
import productAxios from "@/api/Productaxios";
import recipeAxios from "@/api/Recipeaxios";

import Icon from "@/components/Icon.vue";

import Navbar from "@/components/Navbar.vue";

import { Editor, EditorContent, EditorMenuBar } from "tiptap";
import {
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
  History,
  Image
} from "tiptap-extensions";

export default {
  components: {
    EditorContent,
    EditorMenuBar,
    Icon,
    Navbar
  },
  data() {
    return {
      select: [],
      items: [],
      imageFiles: [],
      recipe: {},
      title: "",
      ingredient: "",
      imageNames: [],
      userId: 0,

      editor: new Editor({
        extensions: [
          new Blockquote(),
          new BulletList(),
          new CodeBlock(),
          new HardBreak(),
          new Heading({ levels: [1, 2, 3] }),
          new HorizontalRule(),
          new ListItem(),
          new OrderedList(),
          new TodoItem(),
          new TodoList(),
          new Link(),
          new Bold(),
          new Code(),
          new Italic(),
          new Strike(),
          new Underline(),
          new History(),
          new Image()
          /* new Placeholder({
            emptyNodeClass: "is-empty, is-editor-empty",
            emptyNodeText: "여기에 글을 적어주세요..궁극의 레시피를...!",
            showOnlyWhenEditable: true
          }) */
        ],
        content: `<p>
            궁극의 레시피!
          </ㅔ>
          `
      })
    };
  },
  mounted() {
    productAxios.getProduct(
      res => {
        //console.log(res.data);

        //console.log(res);

        this.items = [];
        res.data.forEach(element => {
          this.items.push({ text: element.name, key: element.id });
        });

        //this.items = res.data;
      },
      err => {
        console.log(err);
      }
    );

    if (sessionStorage.getItem("user") != null) {
      this.userId = JSON.parse(sessionStorage.getItem("user"));
    }
  },
  methods: {
    showImagePrompt(command) {
      this.comm = command;
      //console.log(this.comm);
      this.$refs.image.click();
    },
    readURL(input) {
      var fileList = input.target.files;

      if (input.target.files && input.target.files[0]) {
        var reader = new FileReader();
        reader.onload = e => {
          //console.log(e.target);
          const src = e.target.result;
          this.comm({ src });
          //this.comm = "";
        };
        reader.readAsDataURL(fileList[0]);
      }
    },
    dataURItoBlob(dataURI) {
      var binary = atob(dataURI.split(",")[1]);
      var array = [];
      for (var i = 0; i < binary.length; i++) {
        array.push(binary.charCodeAt(i));
      }
      return new Blob([new Uint8Array(array)], { type: "image/jpeg" });
    },
    preprocessing() {
      //본문 파일 변환
      var tmptext = document.getElementById("tiptaparea").innerHTML;
      var len = tmptext.length;
      tmptext = tmptext.slice(61, len - 6);
      len = tmptext.length;

      let start = 0;
      let last = 0;
      while (start >= 0) {
        start = tmptext.indexOf("data:image", start + 1);
        if (start < 0) break;

        last = tmptext.indexOf('"', start);
        //console.log("start:" + start + " last:" + last);

        let tmp = this.dataURItoBlob(tmptext.substring(start, last));
        //console.log(tmp);

        //let file = new FormData();
        //file.append("file", tmp);
        this.imageFiles.push(new File([tmp], "name"));
      }
      this.$set(this.recipe, "content", tmptext);

      this.filesave();
    },
    filesave() {
      let data = new FormData();
      for (var i = 0; i < this.imageFiles.length; i++)
        data.append("files", this.imageFiles[i]);

      if (data.length == 0) {
        this.submit();
        return;
      }

      recipeAxios.uploadFiles(
        data,
        res => {
          for (var i = 0; i < res.data.length; i++) {
            this.imageNames.push(res.data[i]);
          }
          var index = 0;

          let start = 0;
          let last = 0;
          let tmptext = this.recipe.content;
          while (start >= 0) {
            start = tmptext.indexOf("data:image", start + 1);
            if (start < 0) break;

            //console.log("본분 파일 index:" + index);

            last = tmptext.indexOf('"', start);
            //console.log("start:" + start + " last:" + last);

            var url = tmptext.substring(start, last);
            //console.log(url);
            tmptext = tmptext.replace(
              url,
              `http://127.0.0.1:8080/api/upload/${this.imageNames[index]}`
            );
            index++;
          }

          this.$set(this.recipe, "content", tmptext);

          this.submit();
        },
        err => {
          console.log(err);
        }
      );
    },

    submit() {
      let ingredientProduct = [];
      for (let i = 0; i < this.select.length; i++) {
        ingredientProduct[i] = this.select[i].key;
      }

      let data = {
        userId: 1,

        title: this.title,
        content: this.recipe.content,
        ingredient: this.ingredient,
        ingredientProduct: ingredientProduct
      };
      //console.log("data check");
      //console.log(data);

      recipeAxios.insertRecipe(
        data,
        res => {
          console.log(res);
          alert("작성 완료 되었습니다!");
          //this.$router.push("/reviewdetail/" + res.data);
          this.$router.push("/recipe/");
        },
        error => {
          console.log(error);
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
  },
  beforeDestroy() {
    this.editor.destroy();
  }
};
</script>

<style scoped>
.editor__content ul {
  display: block;
  list-style-type: disc;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  padding-inline-start: 40px;
}

.text-area {
  overflow: hidden;
  position: relative;
  height: 40px;
  margin: 2px 8px 0;
  padding: 6px 10px 1px;
  border-radius: 5px;
  background-color: #f2f3f5;
  margin-bottom: 10px;
}

.ingredient-area {
  overflow: hidden;
  position: relative;
  height: 40px;
  margin-top: 10px;
  margin: 2px 8px 0;
  padding: 6px 10px 1px;
  border-radius: 5px;
  background-color: #f2f3f5;
  margin-bottom: 10px;
}
</style>