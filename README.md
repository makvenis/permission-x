# permission-x
Android权限封装


Android引导页
GuideViewPageLayout view = (GuideViewPageLayout) findViewById(R.id.mGuidePage);
        List<View> views=new ArrayList<>();

        ImageView i1 = new ImageView(this);
        i1.setImageResource(R.drawable.p1);
        i1.setScaleType(ImageView.ScaleType.FIT_XY);
        views.add(i1);

        ImageView i2 = new ImageView(this);
        i2.setImageResource(R.drawable.p2);
        i2.setScaleType(ImageView.ScaleType.FIT_XY);
        views.add(i2);

        ImageView i3 = new ImageView(this);
        i3.setImageResource(R.drawable.p3);
        i3.setScaleType(ImageView.ScaleType.FIT_XY);
        views.add(i3);

        ImageView i4 = new ImageView(this);
        i4.setImageResource(R.drawable.p4);
        i4.setScaleType(ImageView.ScaleType.FIT_XY);
        views.add(i4);

        view.createGuidePage(views);

        /* 触发事件 */
        view.setOnGuideStartOpenCallBack(new GuideViewPageLayout.OnGuideStartOpenCallBack() {
            @Override
            public void open() {
                Toast.makeText(MainActivity.this,"开启之旅",Toast.LENGTH_SHORT).show();
            }
        });
