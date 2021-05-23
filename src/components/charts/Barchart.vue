<script>
import http from '@/core/services/http-common';
import { HorizontalBar } from 'vue-chartjs';

export default {
  extends: HorizontalBar,

  mounted() {
    // Bar가 mount될 때 dongcode로 rating 객체를 얻어옴.
    console.log('barchart mounted');
    console.log(
      this.$store.state.dongStore.Sidogugundong.dongCode + '의 동코드로 평균 별점 정보를 가져온다'
    );
    http
      .get('/dongreview/avg-rating/' + this.$store.state.dongStore.Sidogugundong.dongCode)
      .then(({ data }) => {
        console.log(data);
        this.$store.state.rate.rating = data;
        // mount 될 때 차트에 데이터를 넣음.
        console.log('총! 평균! :      ' + this.$store.state.rate.rating.total);
        //console.log(this.Rating);
        // this.Rating = this.$store.state.rate.rating.total;
        this.renderChart(
          {
            labels: ['환경🌎', '건강💊', '인프라🍙', '안전🚔', '학군🎒', '교통🚦'],

            datasets: [
              {
                font: {
                  size: 14,
                },
                label: '전체 리뷰 통계',
                backgroundColor: 'gray',
                data: [
                  this.$store.state.rate.rating.environment,
                  this.$store.state.rate.rating.health,
                  this.$store.state.rate.rating.infra,
                  this.$store.state.rate.rating.safety,
                  this.$store.state.rate.rating.school,
                  this.$store.state.rate.rating.trans,
                ],
              },
            ],
          },
          { responsive: true, maintainAspectRatio: false }
        );
      })
      .catch(({ response }) => {
        console.log(response);
      });
  },
};
</script>
<style scoped>
canvas {
  font-size: 3cm;
}
</style>
