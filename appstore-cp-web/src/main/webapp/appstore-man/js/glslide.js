/*left right*/
(function($) {
	J$.fn.glSlide=function(opt){
		//settings
		var settings=jQuery.extend(true,{
			productScrollWitch:"ul",//相对this选择器，产生滚动条的大div
			list:"ul > li",//相对this选择器，list对象
			row:1,//分组，即行数（垂直滚动的地方用到，之里只为保持代码同步，所以请先修改垂直滚动）
			seeColumn:1,//可视范围分列数目
			step:1,//滚动步长
			speed:"normal",//滚动速度
			orientation:"left",//自动轮播开启时会以设定方向滚动，否则只作为待滚定位，只有上下，left,right
			isAutoPlay:3000,//自动播放时间间隔
			isBtn:{
				left:"#left",//向上按钮
				right:"#right",//向下按钮
				isChangeState:true//按钮事件是否触发滚动方向状态
			},//按钮树配置
			bugD1Widht:0//table布局情况下经常有取不到width值情况，针对此可进行的补丁操作,正常情况下无需启用该补丁
		},opt);
		//settings
		var productScrollWitch=settings.productScrollWitch,
			list=settings.list,
			row=settings.row,
			seeColumn=settings.seeColumn,
			step=settings.step,
			speed=settings.speed,
			orientation=settings.orientation,
			isAutoPlay=settings.isAutoPlay,
			btn=settings.isBtn,
			btnLeft=$(btn.left),
			btnRight=$(btn.right),
			btnIsChangeState=btn.isChangeState,
			bugD1Widht=settings.bugD1Widht;
		//div
		var $this=$(this);
		var thisselector=$this.selector;
		var d1=$this,
			d2=d1.find(productScrollWitch),
			d3=d1.find(list);
		//d1Widht
		var d1Widht=d1.width();
		if(bugD1Widht!=0){
			d1Widht=bugD1Widht;
		}else{
			if(d1Widht==0){
				alert("Err:d1Widht==0");
			}
		}
		//other width size ...
		var d3OldSize=d3.size();
		var d3Size=d3OldSize*3;
		var splitWidht=d1Widht/seeColumn;
		var stepWidht=step*splitWidht;
		var d2OldWidht=splitWidht*Math.ceil(d3OldSize/row);
		var d2Widht=d2OldWidht*3;
		//fall short of nmuber,return false
		if(d2OldWidht<d1Widht)return false;
		//clone for fill
		for(var i=0;i<d3OldSize;i++){
			d3.eq(i).clone().appendTo(d2);
			d3.eq(d3OldSize-i-1).clone().prependTo(d2);
		}
		//bear with
		d2.width(d2Widht+100);
		//default scrolltop
		d1.scrollLeft(d2OldWidht);
		//
		var flag=true;
		//left
		var left=function(e){
			if(!flag)return false;
			flag=false;
			if(btnIsChangeState){
				orientation="left";
			}
			d1.animate({scrollLeft:d1.scrollLeft()+stepWidht},speed,function(){
				for(i=1;i<=step*row;i++){
					d2.children().eq(0).appendTo(d2);
				}
				d1.scrollLeft(d2OldWidht);
				flag=true;
			});
			return false;
		};
		//right
		var right=function(e){
			if(!flag)return false;
			flag=false;
			if(btnIsChangeState){
				orientation="right";
			}
			//d1.scrollLeft(d2OldWidht);
			d1.animate({scrollLeft:d1.scrollLeft()-stepWidht},speed,function(){
				for(i=1;i<=step*row;i++){
					d2.children().eq(d3Size-1).prependTo(d2);
				}
				d1.scrollLeft(d2OldWidht);
				flag=true;
			});
			return false;
		};
		//timer
		var timerID;
		var autoPlay=function(){
			switch(orientation)
			{
				case "left":timerID=window.setInterval(left,isAutoPlay);break;
				case "right":timerID=window.setInterval(right,isAutoPlay);break;
			}
			return false;
		};
		var autoStop = function(){
			window.clearInterval(timerID);
			return false;
		};
		if(isAutoPlay){
			//ready autoPlay
			autoPlay();
			//
			$this.hover(autoStop,autoPlay);
			if(btn){
				btnLeft.hover(autoStop,autoPlay);
				btnRight.hover(autoStop,autoPlay);
			}
		}
		//btn
		if(btn){
			btnLeft.click(left);
			btnRight.click(right);
		}
		return $this;
	};
})(jQuery);


J$(function(){
	J$("#glSlide").glSlide({
		productScrollWitch:"ul",//相对this选择器，产生滚动条的大div
		list:"ul > li",//相对this选择器，list对象
		seeColumn:2.5,//可视范围分列数目
		step:2,//滚动步长
		speed:1000,//滚动速度
		orientation:"left",//自动轮播开启时会以设定方向滚动，否则只作为待滚定位，只有上下，top,bottom
		isAutoPlay:10000,//自动播放时间间隔
		isBtn:{
		left:"#glSlideLeft",//向上按钮
		right:"#glSlideRight",//向下按钮
		isChangeState:true//按钮事件是否触发滚动方向状态
		}//按钮树配置
	});
});



