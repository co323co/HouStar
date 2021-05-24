<!-- props로 시작좌표와 마커리스트를 넘기면 맵에 찍어준다. -->

<template>
  <!-- 지도를 담을 영역 -->
  <div id="map" style="height: 400px"></div>
</template>

<script>
import kakaoService from '@/core/services/kakao.service.js';
export default {
  props: {
    // 포지션 좌표 넘길 때
    // { lat : 위도, lng : 경도 }
    init_pos: Object,
    // {"position" : {lat:"",lng:""}, "text" : "텍스트 내용" }  형태 마커의 리스트
    //  text는 선택. 없으면 안띄워줌
    marker_list: Array,
  },
  mounted() {
    //실제 지도를 그리는 Javascript API를 불러오기
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement('script');
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${kakaoService.getKey()}`;
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      //지도를 담을 영역의 DOM 레퍼런스
      const container = document.querySelector('#map');
      ///지도를 생성할 때 필요한 기본 옵션
      const options = {
        //지도의 중심좌표. 위도, 경도
        center: new kakao.maps.LatLng(this.init_pos.lat, this.init_pos.lng),
        //지도의 레벨(확대, 축소 정도)
        level: 3,
      };
      // console.log(this.marker_list);
      //지도 생성 및 객체 리턴
      const map = new kakao.maps.Map(container, options);

      let imageSrc = 'media/svg/icons/Map/Marker.png';
      this.marker_list.forEach((marker) => {
        //커스텀 오버레이가 표시될 위치 객체, LatLng(위도, 경도)
        let position = new kakao.maps.LatLng(marker.position.lat, marker.position.lng);
        // 커스텀 오버레이에 표시할 내용
        // HTML 문자열 또는 Dom Element
        let content;
        if (marker.text) {
          content = `
                   <div
                     style="background-color: #FFFFFF;
                     border:solid 3px #ffc129;
                     border-radius: 30px 30px;
                     padding:2px 7px;
                     margin : 5px" >
                       ${marker.text}
                   </div>
                  `;
        }
        content += `
                  <img style="width: 30px" src=${imageSrc}>
        `;
        let customOverlay = new kakao.maps.CustomOverlay({
          position: position,
          content: content,
        });
        // 커스텀 오버레이를 지도에 표시합니다
        customOverlay.setMap(map);
      });
    },
  },
};
</script>

<style scoped>
v-badge {
  color: black;
}
</style>
