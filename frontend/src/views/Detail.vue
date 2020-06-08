<template>
  <div>
    <navbar></navbar>
    <div class="detail-container"></div>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";

export default {
  components: {
    Navbar,
  },
};
</script>

<style>
.detail-container {
  padding: 1rem;
  background: blue;
}
</style>
