package pe.edu.utp.bodega_rb_api.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardMetrics {
  Long dailySales;
  Double dailyRevenue;
  LocalDate[] dailyRevenueDate;
  Double[] dailyRevenueAmount;
  String[] bestSellingCategories;
  Double[] bestSellingCategoriesAmount;
  String[] bestSellingProducts;
  Double[] bestSellingProductsAmount;
  String[] lowStockProducts;
  Double[] lowStockProductsAmount;
}
