<template>
  <div class="membership">
    <div class="inline">
      <img class="skimg" src="@/assets/icons/sktelecom_removebg.png" />
      <b-form-input
        id="input-live"
        v-model="sk"
        :state="skState"
        aria-describedby="input-live-help input-live-feedback"
        placeholder="Enter your numbers"
        trim
      ></b-form-input>
      <b-button @click="RegisterTelMembership('sk')" variant="outline-secondary">등록하기</b-button>
      <b-form-invalid-feedback id="input-live-feedback">등록할 멤버십 번호를 16자리 입력해주세요!</b-form-invalid-feedback>
      <barcode v-bind:value="sk">아직 입력된 SKT 멤버십이 없어요. :(</barcode>
    </div>

    <div class="inline">
      <img class="ktimg" src="@/assets/icons/kt_removebg.png" />
      <b-form-input
        id="input-live"
        v-model="kt"
        :state="ktState"
        aria-describedby="input-live-help input-live-feedback"
        placeholder="Enter your numbers"
        trim
      ></b-form-input>
      <b-button @click="RegisterTelMembership('kt')" variant="outline-secondary">등록하기</b-button>
      <b-form-invalid-feedback id="input-live-feedback">등록할 멤버십 번호를 16자리 입력해주세요!</b-form-invalid-feedback>
      <barcode v-bind:value="kt">아직 입력된 KT 멤버십이 없어요. :(</barcode>
    </div>

    <div class="inline">
      <img class="lgimg" src="@/assets/icons/lguplus_removebg.png" />
      <b-form-input
        id="input-live"
        v-model="lg"
        :state="lgState"
        aria-describedby="input-live-help input-live-feedback"
        placeholder="Enter your numbers"
        trim
      ></b-form-input>
      <b-button @click="RegisterTelMembership('lg')" variant="outline-secondary">등록하기</b-button>
      <b-form-invalid-feedback id="input-live-feedback">등록할 멤버십 번호를 16자리 입력해주세요!</b-form-invalid-feedback>
      <barcode v-bind:value="lg">아직 입력된 LGU+ 멤버십이 없어요. :(</barcode>
    </div>
  </div>
</template>

<script>
import VueBarcode from "vue-barcode";
import BarcodeAxios from "@/api/Barcodeaxios.js";

export default {
  data() {
    return {
      sk: "",
      kt: "",
      lg: "",
      tempnum: "",
      userId: 0
    };
  },
  components: {
    barcode: VueBarcode
  },
  computed: {
    skState() {
      return this.sk.length > 15 ? true : false;
    },
    ktState() {
      return this.kt.length > 15 ? true : false;
    },
    lgState() {
      return this.lg.length > 15 ? true : false;
    }
  },
  mounted() {
    if (sessionStorage.getItem("user") != null) {
      this.userId = JSON.parse(sessionStorage.getItem("user"));
    }
  },
  methods: {
    RegisterTelMembership(sort) {
      if (sort == "sk") {
        this.tempnum = this.sk;
      }
      if (sort == "kt") {
        this.tempnum = this.kt;
      }
      if (sort == "lg") {
        this.tempnum = this.lg;
      }
      BarcodeAxios.insertMembership(
        {
          number: this.tempnum,
          type: sort,
          userId: this.userId
        },
        res => {
          console.log(res);
          alert("멤버십 등록이 완료 되었습니다!");
        },
        error => {
          console.log(error);
          alert("멤버십 등록에 실패했습니다. 다시 요청해주세요!");
        }
      );
    }
  }
};
</script>

<style scoped>
.form-control {
  width: calc(20em + 0.75rem + 2px);
  display: inline;
}
.inline {
  margin: 20px 0;
}
.invalid-feedback {
  width: 100%;
  margin-top: 0;
  margin-bottom: 0.5rem;
  font-size: 1rem;
  color: #dc3545;
}
.btn-outline-secondary {
  color: #34314c;
  border-color: #34314c;
  margin: 5px 10px;
}
.skimg {
  height: 3rem;
  width: 7rem;
  margin: 10px 17.5px;
}
.ktimg {
  height: 2.3rem;
  margin: 10px 46px;
  padding-right: 1rem;
}
.lgimg {
  height: 2.3rem;
  margin: 10px 15px;
}
</style>