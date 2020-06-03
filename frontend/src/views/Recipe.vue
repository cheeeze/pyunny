<template>
  <div>
    <div class="text-area">
      <input
        type="search"
        id="innerQuery"
        class="tf-keyword"
        v-model="keyword"
        v-on:keyup.enter="search"
        title="제목 입력"
        placeholder="레시피 이름!"
        maxlength="100"
        style="font-size: 15px;width:95%; background-color: #f2f3f5; border: none;"
      />
    </div>

    <div class="ingredient-area">
      <input
        type="search"
        id="innerQuery"
        class="tf-keyword"
        v-model="keyword"
        v-on:keyup.enter="search"
        title="재료 입력"
        placeholder="사용 재료!"
        maxlength="100"
        style="width:95%; background-color: #f2f3f5; border: none;"
      />
    </div>

    <v-container fluid>
      <v-row>
        <v-col cols="12">
          <v-combobox v-model="select" :items="items" label="Combobox" multiple outlined dense></v-combobox>
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
    </div>
  </div>
</template>

<script>
import "@/assets/styles/editor/editor.scss";
import "@/assets/styles/editor/main.scss";
import "@/assets/styles/editor/menubar.scss";
import "@/assets/styles/editor/menububble.scss";
import "@/assets/styles/editor/variables.scss";

import Icon from "@/components/Icon.vue";
//import Axios from "@/api/Recipeaxios";

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
    Icon
  },
  data() {
    return {
      select: ["Vuetify", "Programming"],
      items: ["Programming", "Design", "Vue", "Vuetify"],

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
        ],
        content: `
          <h2>
            책쟁이의 책리뷰!
          </h2>
          <p>
            <b>왜 이 책을 보았는가?</b>
          </p>
          <p>직장인분들이라면 공감할 소재도 많은 듯 하다.</s></p>
          <p>
              <code>존맛탱탱탱</code>
          </p>
          <ul >
            <li >
              독서 포인트!
            </li>
            <li >
              하루는 작지만 일년이 지나면 커지고 한 해가 거듭될수록 거대해진다
            </li>
          </ul>
          <blockquote>
          "행동의 씨앗을 뿌리면 습관의 열매가 맺히고,<br />
          습관의 씨앗을 뿌리면 성격의 열매가 맺히고,<br />
          성격의 씨앗을 뿌리면 운명의 열매가 맺힌다."<br />
          - 나폴레옹 -
          </blockquote>
        `
      })
    };
  },
  methods: {
    showImagePrompt(command) {
      this.comm = command;
      console.log(this.comm);
      this.$refs.image.click();
    },
    readURL(input) {
      var fileList = input.target.files;

      if (input.target.files && input.target.files[0]) {
        var reader = new FileReader();
        reader.onload = e => {
          console.log(e.target);
          const src = e.target.result;
          this.comm({ src });
          //this.comm = "";
        };
        reader.readAsDataURL(fileList[0]);
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
  height: 100px;
  margin-top: 10px;
  margin: 2px 8px 0;
  padding: 6px 10px 1px;
  border-radius: 5px;
  background-color: #f2f3f5;
  margin-bottom: 10px;
}
</style>