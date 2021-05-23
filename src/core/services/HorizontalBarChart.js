import { HorizontalBar, mixins } from 'vue-chartjs';
const { reactiveProp } = mixins;

export default {
  extends: HorizontalBar,
  mixins: [reactiveProp],
  props: ['chartdata', 'options'],
  mounted() {
    this.renderChart(this.chartData, this.options, {
      responsive: true,
      maintainAspectRatio: false,
    });
  },
};
