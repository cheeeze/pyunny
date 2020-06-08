<template>
  <vue-tags-input
    placeholder="상품명을 입력해주세요. :-)"
    v-model="tag"
    :tags="tags"
    :autocomplete-items="Products"
    @tags-changed="(newTags) => (tags = newTags)"
  >
    <template slot="autocomplete-header">
      <strong>관심 제품을 추가해주세요!</strong>
    </template>
    <template slot="autocomplete-footer">
      <small>
        <em>또는 정확한 상품명을 입력해주세요!</em>
      </small>
    </template>
  </vue-tags-input>
</template>

<script>
import VueTagsInput from "@johmun/vue-tags-input";

export default {
  components: {
    VueTagsInput,
  },
  data() {
    return {
      tag: "",
      tags: [],
      Products: [
        {
          text: "코카콜라500ml",
        },
        {
          text: "의성마늘햄소세지",
        },
        {
          text: "홈런볼1500",
        },
        {
          text: "농심)새우깡",
        },
        {
          text: "자이언트떡볶이",
        },
        {
          text: "컵)스파게티",
        },
        {
          text: "농심)육개장",
        },
      ],
    };
  },
};
</script>

<style>
.vue-tags-input[data-v-61d92e31] {
  max-width: 80%;
  position: relative;
  background-color: #fff;
  margin: 1rem auto;
}
.ti-tag[data-v-61d92e31] {
  background-color: #91d8ec;
  color: #fff;
  border-radius: 2px;
  display: flex;
  padding: 3px 5px;
  margin: 2px;
  font-size: 1.5em;
}
.ti-new-tag-input-wrapper[data-v-61d92e31] {
  display: flex;
  flex: 1 0 auto;
  padding: 3px 5px;
  margin: 2px;
  font-size: 1.5em;
}
</style>
