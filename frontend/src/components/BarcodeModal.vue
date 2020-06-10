<template>
  <div class="barcode-modal" id="container-wrap" v-if="barcode" @click.self="handleWrapperClick">
    <div class="container">
      <div class="close">
        <button id="close-btn" @click="$emit('update:barcode', !barcode)">
          <img src="@/assets/icons/x.png" alt height="20px" />
        </button>
      </div>
      <div class="checkbox-container">
        <div class="row justify-content-center">
          <div class="conv-tab" style="margin-top: 15px;">
            <input
              class="checkbox-convenience"
              type="checkbox"
              name="convenience"
              id="convenience-1"
            />
            <label class="for-checkbox-convenience" for="convenience-1">
              <img class="conv_icon" src="@/assets/icons/all.png" alt />
            </label>
            <!--
            -->
            <input
              class="checkbox-convenience"
              type="checkbox"
              name="convenience"
              id="convenience-2"
            />
            <label class="for-checkbox-convenience" for="convenience-2">
              <img class="conv_icon" src="@/assets/icons/gs25.png" alt />
            </label>
            <!--
            -->
            <input
              class="checkbox-convenience"
              type="checkbox"
              name="convenience"
              id="convenience-3"
            />
            <label class="for-checkbox-convenience" for="convenience-3">
              <img class="conv_icon" src="@/assets/icons/cu.png" alt />
            </label>
            <!--
            -->
            <input
              class="checkbox-convenience"
              type="checkbox"
              name="convenience"
              id="convenience-4"
            />
            <label class="for-checkbox-convenience" for="convenience-4">
              <img class="conv_icon" src="@/assets/icons/emart.jpg" alt />
            </label>
            <!--
            -->
            <input
              class="checkbox-convenience"
              type="checkbox"
              name="convenience"
              id="convenience-5"
            />
            <label class="for-checkbox-convenience" for="convenience-5">
              <img class="conv_icon" src="@/assets/icons/seven.png" alt />
            </label>
            <!--
            -->
            <input
              class="checkbox-convenience"
              type="checkbox"
              name="convenience"
              id="convenience-6"
            />
            <label class="for-checkbox-convenience" for="convenience-6">
              <img class="conv_icon" src="@/assets/icons/ministop.png" alt />
            </label>
          </div>
        </div>
      </div>

      <h3 id="modal-title">통신사 할인</h3>
      <div class="images">
        <img src="@/assets/icons/kt.png" alt="통신사 로고" height="100px" />
        <img src="@/assets/icons/ktbarcode.png" alt="통신사 바코드" height="100px" />
      </div>
      <h3 id="modal-title">편의점 적립</h3>
      <div class="images">
        <img src="@/assets/icons/gs25.png" alt="편의점 로고" height="100px" />
        <img src="@/assets/icons/gs25barcode.png" alt="통신사 바코드" height="100px" />
      </div>
      <h3 id="modal-title">기프티콘</h3>
      <div class="images">
        <img src="@/assets/icons/gift1.png" alt="편의점 로고" height="200px" style="margin-right: 10px;" />
        <img src="@/assets/icons/gift2.png" alt="통신사 바코드" height="200px" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "barcode-modal",
  props: {
    barcode: {
      type: Boolean,
      require: true,
      default: false
    }
  },
  methods: {
    handleWrapperClick() {
      this.$emit("update:barcode", false);
    }
  }
};
</script>

<style>
:root {
  --white: #ffffff;
  --light: #f0eff3;
  --black: #000000;
  --dark-blue: #1f2029;
  --dark-light: #353746;
  --red: #da2c4d;
  --yellow: #f8ab37;
  --grey: #ecedf3;
}

#container-wrap {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100vw;
  height: 100vh;
  background-color: rgba(255, 255, 255, 0.4);
  z-index: 8;
}

.container {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  position: relative;
  overflow: hidden;
  min-width: 88%;
  min-height: 90%;
}

.close {
  width: 100%;
  padding: 10px 5px;
  text-align: right;
}

#close-btn {
  border: 0;
  outline: none;
  background-color: rgba(255, 255, 255, 0);
  cursor: pointer;
}

.images {
  width: 90%;
}

#modal-title {
  margin-top: 5px;
}

[type="checkbox"]:checked,
[type="checkbox"]:not(:checked) {
  position: absolute;
  left: -9999px;
  width: 0;
  height: 0;
  visibility: hidden;
}
.checkbox:checked + label,
.checkbox:not(:checked) + label {
  position: relative;
  width: 70px;
  display: inline-block;
  padding: 0;
  margin: 0 auto;
  text-align: center;
  margin: 100px 0 30px 55%;
  height: 6px;
  border-radius: 4px;
  background-image: linear-gradient(298deg, var(--red), var(--yellow));
  z-index: 1 !important;
}
.checkbox:checked + label:before,
.checkbox:not(:checked) + label:before {
  position: absolute;
  font-family: "unicons";
  cursor: pointer;
  top: -17px;
  z-index: 2;
  font-size: 20px;
  line-height: 40px;
  text-align: center;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  -webkit-transition: all 300ms linear;
  transition: all 300ms linear;
}
.checkbox:not(:checked) + label:before {
  content: "\eac1";
  left: 0;
  color: var(--yellow);
  background-color: var(--dark-blue);
  box-shadow: 0 4px 4px rgba(0, 0, 0, 0.15), 0 0 0 1px rgba(26, 53, 71, 0.07);
}
.checkbox:checked + label:before {
  content: "\eb8f";
  left: 30px;
  color: var(--grey);
  background-color: var(--white);
  box-shadow: 0 4px 4px rgba(26, 53, 71, 0.25), 0 0 0 1px rgba(26, 53, 71, 0.07);
}

.checkbox:checked ~ .section .checkbox-container .row .conv-tab p {
  color: var(--white);
}
.checkbox-convenience:checked + label,
.checkbox-convenience:not(:checked) + label {
  position: relative;
  display: -webkit-inline-flex;
  display: -ms-inline-flexbox;
  display: inline-flex;
  -webkit-align-items: center;
  -moz-align-items: center;
  -ms-align-items: center;
  align-items: center;
  -webkit-justify-content: center;
  -moz-justify-content: center;
  -ms-justify-content: center;
  justify-content: center;
  -ms-flex-pack: center;
  text-align: center;
  padding: 0;
  padding: 3px;
  font-size: 14px;
  line-height: 30px;
  letter-spacing: 1px;
  margin: 0 1.3vw 16px;
  /* margin-left: 6px;
  margin-right: 6px;
  margin-bottom: 16px; */
  text-align: center;
  border-radius: 4px;
  cursor: pointer;
  color: var(--white);
  text-transform: uppercase;
  background-color: var(--light);
  -webkit-transition: all 300ms linear;
  transition: all 300ms linear;
}
.checkbox-convenience:not(:checked) + label::before {
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
}
.checkbox-convenience:checked + label::before {
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}
.checkbox-convenience:not(:checked) + label:hover::before {
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}
.checkbox-convenience:checked + label::before,
.checkbox-convenience:not(:checked) + label::before {
  position: absolute;
  content: "";
  top: -2px;
  left: -2px;
  width: calc(100% + 4px);
  height: calc(100% + 4px);
  border-radius: 4px;
  z-index: -2;
  background-image: linear-gradient(138deg, var(--red), var(--yellow));
  -webkit-transition: all 300ms linear;
  transition: all 300ms linear;
}
.checkbox-convenience:not(:checked) + label::before {
  top: -1px;
  left: -1px;
  width: calc(100% + 2px);
  height: calc(100% + 2px);
}
.checkbox-convenience:checked + label::after,
.checkbox-convenience:not(:checked) + label::after {
  position: absolute;
  content: "";
  top: -2px;
  left: -2px;
  width: calc(100% + 4px);
  height: calc(100% + 4px);
  border-radius: 4px;
  z-index: -2;
  background-color: var(--light);
  -webkit-transition: all 300ms linear;
  transition: all 300ms linear;
}
.checkbox-convenience:checked + label::after {
  opacity: 0;
}
.checkbox-convenience:checked + label .uil,
.checkbox-convenience:not(:checked) + label .uil {
  font-size: 20px;
}
.checkbox-convenience:checked + label .text,
.checkbox-convenience:not(:checked) + label .text {
  position: relative;
  display: inline-block;
  -webkit-transition: opacity 300ms linear;
  transition: opacity 300ms linear;
}
.checkbox-convenience:checked + label .text {
  opacity: 0.6;
}
.checkbox-convenience:checked + label .text::after,
.checkbox-convenience:not(:checked) + label .text::after {
  position: absolute;
  content: "";
  width: 0;
  left: 0;
  top: 50%;
  margin-top: -1px;
  height: 2px;
  background-image: linear-gradient(138deg, var(--red), var(--yellow));
  z-index: 1;
  -webkit-transition: all 300ms linear;
  transition: all 300ms linear;
}
.checkbox-convenience:not(:checked) + label .text::after {
  width: 0;
}
.checkbox-convenience:checked + label .text::after {
  width: 100%;
}
</style>