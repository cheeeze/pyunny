<template>
  <div>
    <v-list rounded v-show="itemshow||searchshow" style="margin-top:30px;">
      <v-container>
        <v-row>
          <v-col flex="6">
            <v-subheader>편의점 상품</v-subheader>
          </v-col>
          <v-col flex="6">
            <div class="my-2">
              <v-btn small color="primary" v-show="itemshow" @click="itemshow=!itemshow">닫기</v-btn>
            </div>
          </v-col>
        </v-row>
      </v-container>

      <v-list-item v-for="(item, i) in items" :key="i">
        <v-container>
          <v-row>
            <v-list-item-title @click="goToProductDetail(item.id)">{{item.name}}</v-list-item-title>
            <v-col flex="8">
              <v-list-item two-line>
                <v-list-item-content>
                  <v-list-item-subtitle>가격: {{item.price}}원</v-list-item-subtitle>
                  <v-list-item-subtitle style="color:red;">재고: {{item.stockAmount}}</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
              <v-btn small color="yellow" v-show="searchshow" @click="searchMarker(item)">지도보기</v-btn>
            </v-col>
            <v-col flex="4">
              <v-list-item-icon>
                <img :src="item.image" width="100px;" />
              </v-list-item-icon>
            </v-col>
          </v-row>
        </v-container>
      </v-list-item>
    </v-list>
  </div>
</template>
<script>
export default {
  props: {
    itemShow: { type: Boolean },
    searchShow: { type: Boolean },
    items: { type: Array }
  },
  data() {
    return {};
  }
};
</script>
<style scoped>
</style>