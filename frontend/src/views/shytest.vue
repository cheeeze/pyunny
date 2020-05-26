<template>
  <div>
    <h1>{{ message }} - {{ count }}</h1>
    <button v-on:click="plus">+</button>
    <br />
    <button v-on:click="minus">-</button>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      message: "Hello, Vue!!!!!!!!",
      count: 0
    };
  },
  methods: {
    plus: function() {
      this.count++;
    },
    minus: function() {
      this.count--;
    }
  }
};
</script>
