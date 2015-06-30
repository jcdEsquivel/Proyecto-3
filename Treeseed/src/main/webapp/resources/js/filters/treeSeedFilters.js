'use strict';
var treeSeedAppFilters = angular.module('treeSeed.filters', []);
/* Filters */
// need load the moment.js to use this filter. 
  treeSeedAppFilters.filter('fromNow', function() {
    return function(date) {
      return moment(date).fromNow();
    }
  });
