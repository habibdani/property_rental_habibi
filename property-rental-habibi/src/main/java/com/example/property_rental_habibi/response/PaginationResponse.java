package com.example.property_rental_habibi.response;

public class PaginationResponse {
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private int itemsPerPage;

    public PaginationResponse(int currentPage, int totalPages, long totalItems, int itemsPerPage) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
        this.itemsPerPage = itemsPerPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }
}
