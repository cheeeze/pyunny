<template>
  <div>
    <navbar></navbar>
    <div style="margin-top:30px;">
      <editor-content
        id="tiptaparea"
        class="editor__content"
        :editor="editor"
        v-html="recipe.content"
      />
    </div>
  </div>
</template>
<script>
import Navbar from "@/components/Navbar.vue";
import Axios from "@/api/Recipeaxios";
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
} from "tiptap-extensions";
 */
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
      editor: new Editor()
    };
  },
  mounted() {
    Axios.getRecipeById(
      this.id,
      res => {
        console.log(res);
        this.recipe = res.data;
      },
      err => {
        console.log(err);
      }
    );
  }
};
</script>
<style scoped>
</style>