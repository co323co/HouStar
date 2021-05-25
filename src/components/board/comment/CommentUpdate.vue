<template lang="">
 <template>
  <div class="regist">
    <div v-if="modifyComment != null" class="regist_form">
      <div class="mb-2">댓글 수정 :: {{ modifyComment.userid }} : {{ modifyComment.regtime }}</div>
      <div>
        <v-textarea v-model="modifyComment.content" solo></v-textarea>
      </div>
      <div class="mb-5">
        <button class="small" @click="updateCommentCancel">취소</button>
        <button class="small" @click="updateComment">수정</button>
      </div>
    </div>
    <div v-else class="regist_form">
      <div>댓글 등록</div>
      <v-textarea v-model="content" solo></v-textarea>
      <button @click="registComment">등록</button>
    </div>
  </div>
</template>
</template>
<script>
export default {
    
}
</script>
<style lang="">
    
</style>