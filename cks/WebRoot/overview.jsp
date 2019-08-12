<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <title>巡检结果总览</title>
        <meta content="Admin Dashboard" name="description" />
        <meta content="ThemeDesign" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <link rel="shortcut icon" href="assets/images/faviicon.png">

  	  <!-- 公共css -->
  	 <%@ include file="/common/comcss.jspf"%>
    </head>


    <body class="fixed-left">

        <!-- Loader -->
        <div id="preloader"><div id="status"><div class="spinner"></div></div></div>

        <!-- Begin page -->
        <div id="wrapper">
<!-- xyy 引入公共左边菜单 -->        
            <!-- ========== Left Sidebar Start ========== -->
 			<%@include file="/menu/leftSidebar.jsp" %>
            <!-- Left Sidebar End -->

            <!-- Start right Content here -->
            <div class="content-page">
                <!-- Start content -->
                <div class="content">
		<!--引入上边菜单 -->
                    <!-- Top Bar Start -->
	<%@include file="/menu/topbar.jsp" %>
                    <!-- Top Bar End -->
                    <div class="page-content-wrapper ">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="card m-b-30">
                                        <div class="card-body">

                                            <h4 class="mt-0 header-title">Examples</h4>
                                            <p class="text-muted m-b-30 font-14">Bootstrap includes six predefined button styles, each serving its own semantic purpose.</p>

                                            <div class="button-items">
                                                <button type="button" class="btn btn-primary waves-effect waves-light">Primary</button>

                                                <button type="button" class="btn btn-secondary waves-effect">Secondary</button>

                                                <button type="button" class="btn btn-success waves-effect waves-light">Success</button>

                                                <button type="button" class="btn btn-info waves-effect waves-light">Info</button>

                                                <button type="button" class="btn btn-warning waves-effect waves-light">Warning</button>

                                                <button type="button" class="btn btn-danger waves-effect waves-light">Danger</button>

                                                <button type="button" class="btn btn-light waves-effect">Light</button>

                                                <button type="button" class="btn btn-dark waves-effect waves-light">Dark</button>

                                                <button type="button" class="btn btn-link waves-effect">Link</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-6">
                                    <div class="card m-b-30">
                                        <div class="card-body">

                                            <h4 class="mt-0 header-title">Outline buttons</h4>
                                            <p class="text-muted m-b-30 font-14">In need of a button, but not the hefty
                                                background colors they bring? Replace the default modifier classes with
                                                the <code class="highlighter-rouge">.btn-outline-*</code> ones to remove
                                                all background images and colors on any button.</p>

                                            <div class="button-items">
                                                <button type="button" class="btn btn-outline-primary waves-effect waves-light">Primary</button>
                                                <button type="button" class="btn btn-outline-secondary waves-effect">Secondary</button>
                                                <button type="button" class="btn btn-outline-success waves-effect waves-light">Success</button>
                                                <button type="button" class="btn btn-outline-info waves-effect waves-light">Info</button>
                                                <button type="button" class="btn btn-outline-warning waves-effect waves-light">Warning</button>
                                                <button type="button" class="btn btn-outline-danger waves-effect waves-light">Danger</button>
                                                <button type="button" class="btn btn-outline-dark waves-effect waves-light">Dark</button>
                                                <button type="button" class="btn btn-outline-light waves-effect">Light</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="card m-b-30">
                                        <div class="card-body">

                                            <h4 class="mt-0 header-title">Button tags</h4>
                                            <p class="text-muted m-b-30 font-14">The <code class="highlighter-rouge">.btn</code>
                                                classes are designed to be used with the <code
                                                        class="highlighter-rouge">&lt;button&gt;</code> element.
                                                However, you can also use these classes on <code
                                                        class="highlighter-rouge">&lt;a&gt;</code> or <code
                                                        class="highlighter-rouge">&lt;input&gt;</code> elements (though
                                                some browsers may apply a slightly different rendering).</p>

                                            <div class="button-items">
                                                <a class="btn btn-success" href="#" role="button">Link</a>
                                                <button class="btn btn-primary" type="submit">Button</button>
                                                <input class="btn btn-info" type="button" value="Input">
                                                <input class="btn btn-warning" type="submit" value="Submit">
                                                <input class="btn btn-danger" type="reset" value="Reset">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-6">
                                    <div class="card m-b-30">
                                        <div class="card-body">

                                            <h4 class="mt-0 header-title">Sizes</h4>
                                            <p class="text-muted m-b-30 font-14">Fancy larger or smaller buttons? Add
                                                <code class="highlighter-rouge">.btn-lg</code> or <code
                                                        class="highlighter-rouge">.btn-sm</code> for additional sizes.
                                            </p>

                                            <div class="button-items">
                                                <button type="button" class="btn btn-info btn-lg">Large button</button>
                                                <button type="button" class="btn btn-secondary btn-lg">Large button</button>
                                                <button type="button" class="btn btn-info btn-sm">Small button</button>
                                                <button type="button" class="btn btn-secondary btn-sm">Small button</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="card m-b-30">
                                        <div class="card-body">

                                            <h4 class="mt-0 header-title">Block Buttons</h4>
                                            <p class="text-muted m-b-30 font-14">Create block level buttons—those that
                                                span the full width of a parent—by adding <code
                                                        class="highlighter-rouge">.btn-block</code>.</p>

                                            <div class="button-items">
                                                <button type="button" class="btn btn-success btn-lg btn-block">Block level button</button>
                                                <button type="button" class="btn btn-secondary btn-sm btn-block">Block level button</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-6">
                                    <div class="card m-b-30">
                                        <div class="card-body">

                                            <h4 class="mt-0 header-title">Toggle states</h4>
                                            <p class="text-muted m-b-30 font-14">Add <code class="highlighter-rouge">data-toggle="button"</code>
                                                to toggle a button’s <code class="highlighter-rouge">active</code>
                                                state. If you’re pre-toggling a button, you must manually add the <code
                                                        class="highlighter-rouge">.active</code> class
                                                <strong>and</strong> <code
                                                        class="highlighter-rouge">aria-pressed="true"</code> to the
                                                <code class="highlighter-rouge">&lt;button&gt;</code>.
                                            </p>

                                            <div class="button-items">
                                                <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off">
                                                    Single toggle
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="card m-b-30">
                                        <div class="card-body">

                                            <h4 class="mt-0 header-title">Checkbox buttons</h4>
                                            <p class="text-muted m-b-30 font-14">Bootstrap’s <code
                                                    class="highlighter-rouge">.button</code> styles can be applied to
                                                other elements, such as <code class="highlighter-rouge">
                                                    &lt;label&gt;</code>s, to provide checkbox or radio style button
                                                toggling. Add <code
                                                        class="highlighter-rouge">data-toggle="buttons"</code> to a
                                                <code class="highlighter-rouge">.btn-group</code> containing those
                                                modified buttons to enable toggling in their respective styles.</p>

                                            <div class="btn-group btn-group-toggle" data-toggle="buttons">
                                                <label class="btn btn-info active">
                                                    <input type="checkbox" checked autocomplete="off"> Active
                                                </label>
                                                <label class="btn btn-info">
                                                    <input type="checkbox" autocomplete="off"> Check
                                                </label>
                                                <label class="btn btn-info">
                                                    <input type="checkbox" autocomplete="off"> Check
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-6">
                                    <div class="card m-b-30">
                                        <div class="card-body">

                                            <h4 class="mt-0 header-title">Radio buttons</h4>
                                            <p class="text-muted m-b-30 font-14">Bootstrap’s <code
                                                    class="highlighter-rouge">.button</code> styles can be applied to
                                                other elements, such as <code class="highlighter-rouge">
                                                    &lt;label&gt;</code>s, to provide checkbox or radio style button
                                                toggling. Add <code
                                                        class="highlighter-rouge">data-toggle="buttons"</code> to a
                                                <code class="highlighter-rouge">.btn-group</code> containing those
                                                modified buttons to enable toggling in their respective styles.</p>

                                            <div class="btn-group btn-group-toggle" data-toggle="buttons">
                                                <label class="btn btn-light active">
                                                    <input type="radio" name="options" id="option1" autocomplete="off" checked> Active
                                                </label>
                                                <label class="btn btn-light">
                                                    <input type="radio" name="options" id="option2" autocomplete="off"> Radio
                                                </label>
                                                <label class="btn btn-light">
                                                    <input type="radio" name="options" id="option3" autocomplete="off"> Radio
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- end row -->

                        </div><!-- container -->


                    </div> <!-- Page content Wrapper -->

                </div> <!-- content -->

                <footer class="footer">
                    © 2018 Upcube - Crafted with <i class="mdi mdi-heart text-danger"></i> by Themesdesign.
                </footer>

            </div>
            <!-- End Right content here -->

        </div>
        <!-- END wrapper -->

<!-- 公共js -->
   <%@ include file="/common/common.jspf"%>
    </body>
</html>