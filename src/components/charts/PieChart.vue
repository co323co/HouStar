<script>
import { Pie } from 'vue-chartjs';

export default {
  extends: Pie,
  props: ['labels', 'datasets'],
  mounted() {
    // this.gradient = this.$refs.canvas.getContext('2d').createLinearGradient(0, 0, 0, 450);
    // this.gradient2 = this.$refs.canvas.getContext('2d').createLinearGradient(0, 0, 0, 450);

    // this.gradient.addColorStop(0, 'rgba(255, 0,0, 0.5)');
    // this.gradient.addColorStop(0.5, 'rgba(255, 0, 0, 0.25)');
    // this.gradient.addColorStop(1, 'rgba(255, 0, 0, 0)');

    // this.gradient2.addColorStop(0, 'rgba(0, 231, 255, 0.9)');
    // this.gradient2.addColorStop(0.5, 'rgba(0, 231, 255, 0.25)');
    // this.gradient2.addColorStop(1, 'rgba(0, 231, 255, 0)');
    this.renderChart(
      {
        labels: this.labels,
        datasets: this.datasets,
      },

      // {
      // // labels: ['Books', 'Magazines', 'Newspapers'],
      // labels: this.labels,
      // datasets: [
      //   {
      //     backgroundColor: [this.gradient, this.gradient2, '#00D8FF'],
      //     // data: [40, 20, 10],
      //     data: this.data,
      //   },
      // ],
      // },
      {
        responsive: true,
        maintainAspectRatio: false,

        tooltips: {
          enabled: false,
        },
        plugins: {
          datalabels: {
            formatter: (value, ctx) => {
              let datasets = ctx.chart.data.datasets;
              if (datasets.indexOf(ctx.dataset) === datasets.length - 1) {
                let sum = datasets[0].data.reduce((a, b) => a + b, 0);
                let percentage = Math.round((value / sum) * 100) + '%';
                return percentage;
              } else {
                return percentage;
              }
            },
            color: '#fff',
          },
        },
      }
    );
  },
};
</script>
