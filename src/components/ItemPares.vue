<template>
  <div class="item" @click="handleClick" :class="{ boxOpen: isOpen }">
    {{ word }}
  </div>
</template>

<script>
export default {
  props: {
    word: {
      type: String,
      required: true,
    },
    isOpen: {
      type: Boolean,
      required: true,
    },
  },
  methods: {
    handleClick() {
      if (!this.isOpen) { 
        this.$emit('itemClicked', this.word); 
      }
    },
  },
};
</script>

<style scoped>
.item {
  position: relative;
  width: 155px;
  height: 155px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.5em;
  background: #ffffff;
  transition: 0.25s;
  border-radius: 20px;
}

.boxOpen {
  transform: rotateY(0deg);
}

.item::after {
  content: '';
  position: absolute;
  inset: 0;
  background: #A3BAD5;
  border-radius: 20px; 
  transform: rotateY(0deg);
  transition: 0.25s;
  backface-visibility: hidden;
  box-shadow: 10px 10px 4px rgba(0, 0, 0, 0.3);
}

.boxOpen::after {
  transform: rotateY(180deg);
  backface-visibility: hidden;

}
</style>
