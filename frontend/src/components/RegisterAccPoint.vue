<template>
  <div class="membership">

    <div class="inline">
      <img class="gsimg" src="@/assets/icons/gs25.png" />
      <b-form-input
        id="input-live"
        v-model="gs"
        :state="gsState"
        aria-describedby="input-live-help input-live-feedback"
        placeholder="Enter your numbers"
        trim
      ></b-form-input>
      <b-button @click="RegisterMembership('gs')" variant="outline-secondary">등록하기</b-button>
      <b-form-invalid-feedback id="input-live-feedback"
        >GS멤버십 번호를 입력해주세요!</b-form-invalid-feedback
      >
      <barcode v-bind:value="gs">
        아직 입력된 GS멤버십이 없어요. :(
      </barcode>
    </div>

    <div class="inline">
      <img class="cuimg" src="@/assets/icons/cu.png" />
      <b-form-input
        id="input-live"
        v-model="cu"
        :state="cuState"
        aria-describedby="input-live-help input-live-feedback"
        placeholder="Enter your numbers"
        trim
      ></b-form-input>
      <b-button @click="RegisterMembership('cu')" variant="outline-secondary">등록하기</b-button>

      <b-form-invalid-feedback id="input-live-feedback"
        >CU멤버십 번호를 입력해주세요!</b-form-invalid-feedback
      >
      <barcode v-bind:value="cu">
        아직 입력된 CU멤버십이 없어요. :(
      </barcode>
    </div>

    <div class="inline">
      <img class="sevenimg" src="@/assets/icons/seven.png" />
      <b-form-input
        id="input-live"
        v-model="seven"
        :state="sevenState"
        aria-describedby="input-live-help input-live-feedback"
        placeholder="Enter your numbers"
        trim
      ></b-form-input>
      <b-button @click="RegisterMembership('seven')" variant="outline-secondary">등록하기</b-button>

      <b-form-invalid-feedback id="input-live-feedback"
        >L.POINT 번호를 입력해주세요!</b-form-invalid-feedback
      >
      <barcode v-bind:value="seven">
        아직 입력된 L.POINT가 없어요. :(
      </barcode>
    </div>

    <div class="inline">
      <img class="emartimg" src="@/assets/icons/emart.jpg" />
      <b-form-input
        id="input-live"
        v-model="emart"
        :state="emartState"
        aria-describedby="input-live-help input-live-feedback"
        placeholder="Enter your numbers"
        trim
      ></b-form-input>
      <b-button @click="RegisterMembership('emart')" variant="outline-secondary">등록하기</b-button>

      <b-form-invalid-feedback id="input-live-feedback"
        >신세계 멤버십 번호를 입력해주세요!</b-form-invalid-feedback
      >
      <barcode v-bind:value="emart">
        아직 입력된 신세계 멤버십이 없어요. :(
      </barcode>
    </div>

    <div class="inline">
      <img class="ministopimg" src="@/assets/icons/ministop.png" />
      <b-form-input
        id="input-live"
        v-model="ministop"
        :state="ministopState"
        aria-describedby="input-live-help input-live-feedback"
        placeholder="Enter your numbers"
        trim
      ></b-form-input>
      <b-button @click="RegisterMembership('ministop')" variant="outline-secondary">등록하기</b-button>

      <b-form-invalid-feedback id="input-live-feedback"
        >OKcashbag 멤버십 번호를 입력해주세요!</b-form-invalid-feedback
      >
        <barcode v-bind:value="ministop">
        아직 입력된 OKcashbag 멤버십이 없어요. :(
      </barcode>
    </div>
  </div>

</template>

<script>
import VueBarcode from "vue-barcode";
import BarcodeAxios from "@/api/Barcodeaxios.js";

export default {
  data() {
    return {
      gs: "",
      cu: "",
      emart: "",
      seven: "",
      ministop: "",
      tempnum: "",
    };
  },
  components: {
    'barcode': VueBarcode
  },
  computed: {
    gsState() {
      return this.gs.length > 15 ? true : false;
    },
    cuState() {
      return this.cu.length > 15 ? true : false;
    },
    emartState() {
      return this.emart.length > 15 ? true : false;
    },
    sevenState() {
      return this.seven.length > 15 ? true : false;
    },
    ministopState() {
      return this.ministop.length > 15 ? true : false;
    },
  },
  methods: {
    RegisterMembership(sort) {
      console.log(sort)
      if (sort == 'gs'){
        this.tempnum = this.gs
      } if (sort == 'cu'){
        this.tempnum = this.cu
      } if (sort == 'seven') {
        this.tempnum = this.seven
        console.log(this.tempnum)
      } if (sort == 'emart') {
        this.tempnum = this.emart
      } if (sort == 'ministop') {
        this.tempnum = this.ministop
      }
      BarcodeAxios.insertMembership(
        {
          number: this.tempnum,
          type: sort,
          userId: 1
        },
        res => {
          console.log(res);
          alert('멤버십 등록이 완료 되었습니다!');
        },
        error => {
          console.log(error);
          alert('멤버십 등록에 실패했습니다. 다시 요청해주세요!')
        }
      )
    },
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
.gsimg {
  height: 3.5rem;
  width: 4rem;
  margin: 0px 20px;
}
.cuimg {
  height: 3.5rem;
  margin: 0px 25px;
}
.sevenimg {
  height: 2.8rem;
  margin: 0px 30px;
}
.emartimg {
  height: 3.3rem;
  margin: 0px 28px;
}
.ministopimg {
  height: 3.8rem;
  margin: 0px 21px;
}
</style>
