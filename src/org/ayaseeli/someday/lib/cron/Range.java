package org.ayaseeli.someday.lib.cron;

class Range {
    
    private int from;
    private int to;
    
    Range(int from, int to) {
        this.from = from;
        this.to = to;
    }
    
    public boolean contains(int search) {
        return search >= from && search <= to;
    }
    
    public int getFrom() {
        return from;
    }
    
    public int getTo() {
        return to;
    }

}
