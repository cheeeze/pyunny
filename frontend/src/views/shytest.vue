<template>
  <div>
    <div class="mt-3">
      내 닉네임:
      <span>{{ submittedNames }}</span>
    </div>
    <b-button v-b-modal.modal-prevent-closing>변경하기</b-button>
    <b-modal
      id="modal-prevent-closing"
      ref="modal"
      title="변경할 닉네임을 입력해주세요."
      @show="resetModal"
      @hidden="resetModal"
      @ok="handleOk"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group
          :state="nameState"
          label="닉네임"
          label-for="name-input"
          invalid-feedback="닉네임을 입력해주세요!"
        >
          <b-form-input
            id="name-input"
            v-model="name"
            :state="nameState"
            required
          ></b-form-input>
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: "",
      nameState: null,
      submittedNames: "야호",
    };
  },
  methods: {
    checkFormValidity() {
      const valid = this.$refs.form.checkValidity();
      this.nameState = valid;
      return valid;
    },
    resetModal() {
      this.name = "";
      this.nameState = null;
    },
    handleOk(bvModalEvt) {
      bvModalEvt.preventDefault();
      this.handleSubmit();
    },
    handleSubmit() {
      if (!this.checkFormValidity()) {
        return;
      }
      this.submittedNames = this.name;
      this.$nextTick(() => {
        this.$bvModal.hide("modal-prevent-closing");
      });
    },
  },
};
</script>

<style scoped>
.inline {
  display: inline;
}
</style>
