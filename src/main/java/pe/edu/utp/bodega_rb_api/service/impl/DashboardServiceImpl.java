package pe.edu.utp.bodega_rb_api.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.edu.utp.bodega_rb_api.dto.DashboardMetrics;
import pe.edu.utp.bodega_rb_api.model.Producto;
import pe.edu.utp.bodega_rb_api.repository.ComprobanteRepository;
import pe.edu.utp.bodega_rb_api.repository.ProductoRepository;
import pe.edu.utp.bodega_rb_api.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {
  @Autowired
  private ComprobanteRepository comprobanteRepository;

  @Autowired
  private ProductoRepository productoRepository;

  @Override
  public DashboardMetrics getDashboardMetrics() {
    DashboardMetrics dashboardMetrics = new DashboardMetrics();
    dashboardMetrics.setDailySales(comprobanteRepository.dailySales());
    dashboardMetrics.setDailyRevenue(comprobanteRepository.dailyRevenue());

    LocalDate last30Days = LocalDate.now().minusDays(30);
    Pageable top10 = PageRequest.of(0, 10);

    List<Object[]> dailyRevenues = comprobanteRepository.dailyRevenues(last30Days);

    dashboardMetrics.setDailyRevenueDate(dailyRevenues.stream().map(o -> (LocalDate) o[0]).toArray(LocalDate[]::new));
    dashboardMetrics.setDailyRevenueAmount(dailyRevenues.stream().map(o -> (Double) o[1]).toArray(Double[]::new));

    List<Object[]> top10Products = comprobanteRepository.bestSellingProducts(last30Days, top10);

    dashboardMetrics.setBestSellingProducts(top10Products.stream().map(o -> (String) o[0]).toArray(String[]::new));
    dashboardMetrics
        .setBestSellingProductsAmount(top10Products.stream().map(o -> (Double) o[1]).toArray(Double[]::new));

    List<Object[]> top10Categories = comprobanteRepository.bestSellingCategories(last30Days);

    dashboardMetrics.setBestSellingCategories(top10Categories.stream().map(o -> (String) o[0])
        .toArray(String[]::new));
    dashboardMetrics.setBestSellingCategoriesAmount(top10Categories.stream().map(o -> (Double) o[1])
        .toArray(Double[]::new));

    List<Producto> lowStockProducts = productoRepository.findByStockLessThan(10d);

    System.out.println(lowStockProducts);

    dashboardMetrics.setLowStockProducts(lowStockProducts.stream().map(p -> p.getNombre())
        .toArray(String[]::new));
    dashboardMetrics.setLowStockProductsAmount(lowStockProducts.stream().map(p -> p.getStock())
        .toArray(Double[]::new));

    return dashboardMetrics;
  }
}
