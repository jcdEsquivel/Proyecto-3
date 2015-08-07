'use strict';

var treeSeedAppDirectives = angular.module('treeSeedDirectives', []);

treeSeedAppDirectives.directive("imagedrop", function($parse, $document) {
	return {
		restrict : "A",
		link : function(scope, element, attrs) {
			var onImageDrop = $parse(attrs.onImageDrop);

			// When an item is dragged over the document
			var onDragOver = function(e) {
				e.preventDefault();
				angular.element('divIm').addClass("dragOver");
			};

			// When the user leaves the window, cancels the drag or drops the
			// item
			var onDragEnd = function(e) {
				e.preventDefault();
				angular.element('divIm').removeClass("dragOver");
			};

			// When a file is dropped
			var loadFile = function(file) {
				scope.uploadedFile = file;
				scope.$apply(onImageDrop(scope));
			};

			// Dragging begins on the document
			$document.bind("dragover", onDragOver);

			// Dragging ends on the overlay, which takes the whole window
			element.bind("dragleave", onDragEnd).bind("drop", function(e) {
				onDragEnd(e);
				loadFile(e.originalEvent.dataTransfer.files[0]);
			});
		}
	};
});

treeSeedAppDirectives.directive('setNgAnimate', [ '$animate',
		function($animate) {
			return {
				link : function($scope, $element, $attrs) {
					$scope.$watch(function() {
						return $scope.$eval($attrs.setNgAnimate, $scope);
					}, function(valnew, valold) {
						$animate.enabled(!!valnew, $element);
					});
				}
			};
		} ]);

treeSeedAppDirectives.directive('uiButterbar', [
		'$rootScope',
		'$anchorScroll',
		function($rootScope, $anchorScroll) {
			return {
				restrict : 'AC',
				template : '<span class="bar"></span>',
				link : function(scope, el, attrs) {
					el.addClass('butterbar hide');
					scope.$on('$stateChangeStart', function(event) {
						$anchorScroll();
						el.removeClass('hide').addClass('active');
					});
					scope.$on('$stateChangeSuccess', function(event, toState,
							toParams, fromState) {
						event.targetScope.$watch('$viewContentLoaded',
								function() {
									el.addClass('hide').removeClass('active');
								})
					});
				}
			};
		} ]);

treeSeedAppDirectives.directive('uiFocus', function($timeout, $parse) {
	return {
		link : function(scope, element, attr) {
			var model = $parse(attr.uiFocus);
			scope.$watch(model, function(value) {
				if (value === true) {
					$timeout(function() {
						element[0].focus();
					});
				}
			});
			element.bind('blur', function() {
				scope.$apply(model.assign(scope, false));
			});
		}
	};
});

treeSeedAppDirectives.directive('uiDatepickerRange', function() {
	 return {
	      restrict: 'A',
	      link: function(scope, element) {

	        element.bind('click', function(e) {
	            angular.element(e.target).siblings('#dateRange').trigger('jq');
	        });
	      }
	    };
	});


treeSeedAppDirectives
		.directive(
				'uiFullscreen',
				[
						'uiLoad',
						'JQ_CONFIG',
						'$document',
						'$window',
						function(uiLoad, JQ_CONFIG, $document, $window) {
							return {
								restrict : 'AC',
								template : '<i class="fa fa-expand fa-fw text"></i><i class="fa fa-compress fa-fw text-active"></i>',
								link : function(scope, el, attr) {
									el.addClass('hide');
									uiLoad
											.load(JQ_CONFIG.screenfull)
											.then(
													function() {
														// disable on ie11
														if (screenfull.enabled
																&& !navigator.userAgent
																		.match(/Trident.*rv:11\./)) {
															el
																	.removeClass('hide');
														}
														el
																.on(
																		'click',
																		function() {
																			var target;
																			attr.target
																					&& (target = $(attr.target)[0]);
																			screenfull
																					.toggle(target);
																		});
														$document
																.on(
																		screenfull.raw.fullscreenchange,
																		function() {
																			if (screenfull.isFullscreen) {
																				el
																						.addClass('active');
																			} else {
																				el
																						.removeClass('active');
																			}
																		});
													});
								}
							};
						} ]);

treeSeedAppDirectives.directive('uiModule', [
		'MODULE_CONFIG',
		'uiLoad',
		'$compile',
		function(MODULE_CONFIG, uiLoad, $compile) {
			return {
				restrict : 'A',
				compile : function(el, attrs) {
					var contents = el.contents().clone();
					return function(scope, el, attrs) {
						el.contents().remove();
						uiLoad.load(MODULE_CONFIG[attrs.uiModule]).then(
								function() {
									$compile(contents)(scope,
											function(clonedElement, scope) {
												el.append(clonedElement);
											});
								});
					}
				}
			};
		} ]);

treeSeedAppDirectives
		.directive(
				'uiNav',
				[
						'$timeout',
						function($timeout) {
							return {
								restrict : 'AC',
								link : function(scope, el, attr) {
									var _window = $(window), _mb = 768, wrap = $('.app-aside'), next, backdrop = '.dropdown-backdrop';
									// unfolded
									el
											.on(
													'click',
													'a',
													function(e) {
														next
																&& next
																		.trigger('mouseleave.nav');
														var _this = $(this);
														_this
																.parent()
																.siblings(
																		".active")
																.toggleClass(
																		'active');
														_this.next().is('ul')
																&& _this
																		.parent()
																		.toggleClass(
																				'active')
																&& e
																		.preventDefault();
														// mobile
														_this.next().is('ul')
																|| ((_window
																		.width() < _mb) && $(
																		'.app-aside')
																		.removeClass(
																				'show off-screen'));
													});

									// folded & fixed
									el
											.on(
													'mouseenter',
													'a',
													function(e) {
														next
																&& next
																		.trigger('mouseleave.nav');
														$('> .nav', wrap)
																.remove();
														if (!$('.app-aside-fixed.app-aside-folded').length
																|| (_window
																		.width() < _mb)
																|| $('.app-aside-dock').length)
															return;
														var _this = $(e.target), top, w_h = $(
																window)
																.height(), offset = 50, min = 150;

														!_this.is('a')
																&& (_this = _this
																		.closest('a'));
														if (_this.next().is(
																'ul')) {
															next = _this.next();
														} else {
															return;
														}

														_this
																.parent()
																.addClass(
																		'active');
														top = _this.parent()
																.position().top
																+ offset;
														next.css('top', top);
														if (top + next.height() > w_h) {
															next.css('bottom',
																	0);
														}
														if (top + min > w_h) {
															next
																	.css(
																			'bottom',
																			w_h
																					- top
																					- offset)
																	.css('top',
																			'auto');
														}
														next.appendTo(wrap);

														next
																.on(
																		'mouseleave.nav',
																		function(
																				e) {
																			$(
																					backdrop)
																					.remove();
																			next
																					.appendTo(_this
																							.parent());
																			next
																					.off(
																							'mouseleave.nav')
																					.css(
																							'top',
																							'auto')
																					.css(
																							'bottom',
																							'auto');
																			_this
																					.parent()
																					.removeClass(
																							'active');
																		});

														$('.smart').length
																&& $(
																		'<div class="dropdown-backdrop"/>')
																		.insertAfter(
																				'.app-aside')
																		.on(
																				'click',
																				function(
																						next) {
																					next
																							&& next
																									.trigger('mouseleave.nav');
																				});

													});

									wrap.on('mouseleave', function(e) {
										next && next.trigger('mouseleave.nav');
										$('> .nav', wrap).remove();
									});
								}
							};
						} ]);

treeSeedAppDirectives.directive('uiScrollTo', [ '$location', '$anchorScroll',
		function($location, $anchorScroll) {
			return {
				restrict : 'AC',
				link : function(scope, el, attr) {
					el.on('click', function(e) {
						$location.hash(attr.uiScrollTo);
						$anchorScroll();
					});
				}
			};
		} ]);

treeSeedAppDirectives.directive('uiShift',
		[
				'$timeout',
				function($timeout) {
					return {
						restrict : 'A',
						link : function(scope, el, attr) {
							// get the $prev or $parent of this el
							var _el = $(el), _window = $(window), prev = _el
									.prev(), parent, width = _window.width();

							!prev.length && (parent = _el.parent());

							function sm() {
								$timeout(function() {
									var method = attr.uiShift;
									var target = attr.target;
									_el.hasClass('in')
											|| _el[method](target).addClass(
													'in');
								});
							}

							function md() {
								parent && parent['prepend'](el);
								!parent && _el['insertAfter'](prev);
								_el.removeClass('in');
							}

							(width < 768 && sm()) || md();

							_window
									.resize(function() {
										if (width !== _window.width()) {
											$timeout(function() {
												(_window.width() < 768 && sm())
														|| md();
												width = _window.width();
											});
										}
									});
						}
					};
				} ]);

treeSeedAppDirectives
		.directive(
				'uiToggleClass',
				[
						'$timeout',
						'$document',
						function($timeout, $document) {
							return {
								restrict : 'AC',
								link : function(scope, el, attr) {
									el
											.on(
													'click',
													function(e) {
														e.preventDefault();
														var classes = attr.uiToggleClass
																.split(','), targets = (attr.target && attr.target
																.split(','))
																|| Array(el), key = 0;
														angular
																.forEach(
																		classes,
																		function(
																				_class) {
																			var target = targets[(targets.length && key)];
																			(_class
																					.indexOf('*') !== -1)
																					&& magic(
																							_class,
																							target);
																			$(
																					target)
																					.toggleClass(
																							_class);
																			key++;
																		});
														$(el).toggleClass(
																'active');

														function magic(_class,
																target) {
															var patt = new RegExp(
																	'\\s'
																			+ _class
																					.replace(
																							/\*/g,
																							'[A-Za-z0-9-_]+')
																					.split(
																							' ')
																					.join(
																							'\\s|\\s')
																			+ '\\s',
																	'g');
															var cn = ' '
																	+ $(target)[0].className
																	+ ' ';
															while (patt
																	.test(cn)) {
																cn = cn
																		.replace(
																				patt,
																				' ');
															}
															$(target)[0].className = $
																	.trim(cn);
														}
													});
								}
							};

						} ]);

treeSeedAppDirectives.directive("isEmailUnique", function($http,
		$uniqueDataService) {
	return {
		restrict : 'A',
		require : 'ngModel',
		link : function(scope, element, attrs, ngModel) {
			element.bind('blur', function(e) {// blur-> after input losses
				// focus
				var currentValue = element.val();
				$uniqueDataService.isEmailUnique(currentValue).then(
						function(value) {
							console.log('restuldo: ' + value);
							ngModel.$setValidity('unique', value);

						});
			});
		}
	}
});
treeSeedAppDirectives.directive('loginDialog', function(AUTH_EVENTS) {
	return {
		restrict : 'A',
		template : '<div ng-if="visible" ng-include="\'login-form.html\'">',
		link : function(scope) {
			var showDialog = function() {
				scope.visible = true;
			};

			scope.visible = false;
			scope.$on(AUTH_EVENTS.notAuthenticated, function($scope) {
				$scope.animationsEnabled = true;

				var modalInstance = $modal.open({
					animation : $scope.animationsEnabled,
					templateUrl : 'layouts/components/page_login.html',
					controller : 'loginController'
				});

				$scope.toggleAnimation = function() {
					$scope.animationsEnabled = !$scope.animationsEnabled;
				};
			});
			scope.$on(AUTH_EVENTS.sessionTimeout, function($scope) {
				$scope.animationsEnabled = true;

				var modalInstance = $modal.open({
					animation : $scope.animationsEnabled,
					templateUrl : 'layouts/components/page_login.html',
					controller : 'loginController'
				});

				$scope.toggleAnimation = function() {
					$scope.animationsEnabled = !$scope.animationsEnabled;
				};
			})
		}
	};
})

.directive('formAutofillFix', function ($timeout) {
  return function (scope, element, attrs) {
    element.prop('method', 'post');
    if (attrs.ngSubmit) {
      $timeout(function () {
        element
          .unbind('submit')
          .bind('submit', function (event) {
            event.preventDefault();
            element
              .find('input, textarea, select')
              .trigger('input')
              .trigger('change')
              .trigger('keydown');
            scope.$apply(attrs.ngSubmit);
          });
      });
    }
  };
});


treeSeedAppDirectives.directive('treeSeed', function() {
  return {
    restrict: 'EA',
    scope: {},
    link: function(scope, element, attrs) {
      d3().then(function(d3) {
    	  var margin = {
    				top : 20,
    				right : 120,
    				bottom : 20,
    				left : 120
    			}, width = 960 - margin.right - margin.left, height = 800 - margin.top
    					- margin.bottom;
    			//Duración de animacion de carga de los links
    			var i = 0, duration = 1500, root;

    			var tree = d3.layout.tree().size([ height, width ]);

    			var diagonal = d3.svg.diagonal().projection(function(d) {
    				return [ d.y, d.x ];
    			});

    			var svg = d3.select("body").append("svg").attr("width",
    					width + margin.right + margin.left).attr("height",
    					height + margin.top + margin.bottom).append("g").attr(
    					"transform",
    					"translate(" + margin.left + "," + margin.top + ")");

    			//Carga
    			d3.json("resources/js/libs/d3/flare.json", function(error, flare) {
    				if (error)
    					throw error;
    				//Animacion de carga
    				root = flare;
    				root.x0 = height / 2;
    				root.y0 = 0;
    				//Conlapsa todos los nodos al carga
    				function collapse(d) {
    					if (d.children) {
    						d._children = d.children;
    						d._children.forEach(collapse);
    						d.children = null;
    					}
    				}

    				root.children.forEach(collapse);
    				update(root);
    			});

    			d3.select(self.frameElement).style("height", "800px");
    			/*
    			d3.select(self.frameElement).styel("transform","rotate(90deg) scale(1) skew(1deg) translate(0px)");
    			d3.select(self.frameElement).styel("-webkit-transform","rotate(90deg) scale(1) skew(1deg) translate(0px)");
    			d3.select(self.frameElement).styel("-moz-transform","rotate(90deg) scale(1) skew(1deg) translate(0px)");
    			d3.select(self.frameElement).styel("-o-transform","rotate(90deg) scale(1) skew(1deg) translate(0px)");
    			d3.select(self.frameElement).styel("-ms-transform","rotate(90deg) scale(1) skew(1deg) translate(0px)");

    			function update(source) {

    				// Compute the new tree layout.
    				var nodes = tree.nodes(root).reverse(), links = tree.links(nodes);

    				// Normalize for fixed-depth.
    				//Largo de los links
    				nodes.forEach(function(d) {
    					d.y = d.depth * 100;
    				});

    				// Update the nodes…
    				var node = svg.selectAll("g.node").data(nodes, function(d) {
    					return d.id || (d.id = ++i);
    				});

    				// Enter any new nodes at the parent's previous position.
    				var nodeEnter = node.enter().append("g").attr("class", "node")
    						.attr(
    								"transform",
    								function(d) {
    									return "translate(" + source.y0 + ","
    											+ source.x0 + ")";
    								}).on("click", click);
    				//Estilo del circulo al inicio de la carga(Animacion)
    				nodeEnter.append("circle").attr("r", 1e-6).style("fill",
    						function(d) {
    							return d._children ? "lightsteelblue" : "#fff";
    						});
    				//Propiedades del texto
    				nodeEnter.append("text").attr("x", function(d) {
    					return d.children || d._children ? -10 : 10;//Posicion en X con respento al borde derecho
    					//})nodeEnter.append("text").attr("y", function(d) {
    					//	return d.children || d._children ? -20 : 20;Posicion en y con respento al centro del circulo
    				}).attr("dy", ".35em").attr("text-anchor", function(d) {
    					return d.children || d._children ? "end" : "start";
    				}).text(function(d) {
    					return d.name;
    				}).style("fill-opacity", 1e-6);

    				// Transition nodes to their new position.
    				var nodeUpdate = node.transition().duration(duration).attr(
    						"transform", function(d) {
    							return "translate(" + d.y + "," + d.x + ")";
    						});
    				//Radio del circulo luego de la primera carga
    				nodeUpdate.select("circle").attr("r", 15).style(
    						"fill",
    						function(d) {
    							return d._children ? "url(#image)" : "url(#" + d.id
    									+ ")";
    						});

    				nodeUpdate.select("text").style("fill-opacity", 1);

    				// Transition exiting nodes to the parent's new position.
    				var nodeExit = node.exit().transition().duration(duration).attr(
    						"transform", function(d) {
    							return "translate(" + source.y + "," + source.x + ")";
    						}).remove();

    				nodeExit.select("circle").attr("r", 1e-6);

    				nodeExit.select("text").style("fill-opacity", 1e-6);

    				// Update the links…
    				var link = svg.selectAll("path.link").data(links, function(d) {
    					return d.target.id;
    				});

    				// Enter any new links at the parent's previous position.
    				link.enter().insert("path", "g").attr("class", "link").attr("d",
    						function(d) {
    							var o = {
    								x : source.x0,
    								y : source.y0
    							};
    							return diagonal({
    								source : o,
    								target : o
    							});
    						});

    				// Transition links to their new position.
    				link.transition().duration(duration).attr("d", diagonal);

    				// Transition exiting nodes to the parent's new position.
    				link.exit().transition().duration(duration).attr("d", function(d) {
    					var o = {
    						x : source.x,
    						y : source.y
    					};
    					return diagonal({
    						source : o,
    						target : o
    					});
    				}).remove();

    				// Stash the old positions for transition.
    				nodes.forEach(function(d) {
    					d.x0 = d.x;
    					d.y0 = d.y;
    				});
    			}

    			// Toggle children on click.
    			function click(d) {
    				if (d.children) {
    					d._children = d.children;
    					d.children = null;
    				} else {
    					d.children = d._children;
    					d._children = null;
    				}
    				update(d);
    			}
      });
    }};
}]);
