# setwd("Z:\\RusynLab\\Cell Culture Lab\\Concawe Screening\\iPSC cardiomyocytes\\R Processing\\Total Cells - 24 hrs")
setwd("C:\\Users\\ssingh\\Desktop\\Cardio_Total_Cell_24h\test")
if(file.exists(".\\Figs\\Cardio_total_cell_24h1.png")){
	quit()}					# modified this line 
source("fitfunctions.txt")

#install.packages("pROC")
library(pROC)

w2=read.csv("Data.csv")
dmso=read.csv("Vehicle.csv")
rowblanks=grep(0,nchar(as.vector(dmso[,1])))
if (length(rowblanks)>0){dmso=dmso[-rowblanks,]}
rowblanks=grep(T,nchar(as.vector(w2[,1]))==0)
if (length(rowblanks)>0){w2=w2[-rowblanks,]}

controls=as.vector(unlist(dmso[,2])) # the 9:14 may change
hist(controls)

conc=c(rep(0.01,length(controls)),rep(0.1,1),rep(1,1),rep(10,1),rep(100,1)) 

startcol=2
endcol=5

x=log10(conc)
numchem=dim(w2)[1]
w2pod=matrix(NA,dim(w2)[1],3)
w2podupper=matrix(NA,dim(w2)[1],3)
EC=matrix(NA,dim(w2)[1],3)
direction=rep(NA,dim(w2)[1])
mychem=w2$Chemical_name

#for (i in (1:numchem)){
# pdf("TC_24hrs_Data.pdf",height=5,width=5)		# modified this line 
for (i in (1:numchem)){ 
	file_name = paste(".\\Figs\\Cardio_total_cell_24h", i, ".png", sep="")
    png(file_name) 						# modified this line 
 noncontrols=unlist(w2[i,startcol:endcol])
 if (sd(noncontrols)>0){
 y=c(controls,noncontrols)
 #polyfit(x,y,plot.it=T)
 #title(mytitle)
 #locator(1)
 mytitle=paste(as.character(w2[i,1]),i)
 result=together.logit4(x,y,plot.it=T,threshold=-25)
 w2pod[i,]=result$bmd
 w2podupper[i,]=result$bmdupper
 EC[i,]=result$EC
 direction[i]=result$direction
 title(mytitle)
 #locator(1)
 }
 print(i)
	dev.off()				# modified this line 
 }


######### AUC NOT CHECKED BELOW!!! 2/18/15
########## Now look at activity vs. POD
par(mfrow=c(3,4))
active=1-w2$Control
final.auc=rep(0,12)
for (j in (1:12)){
 currentroc= roc(active,w2pod[,j],auc=T,plot=T)
 title(round(currentroc$auc,4))
 final.auc[j]=currentroc$auc
 }
###############
###############

## Now output results
colnames(w2pod)=paste("pod.sd",1:3,sep="")
colnames(w2podupper)=paste("pod.sd.upper",1:3,sep="")
colnames(EC)=c("EC10","EC50","EC90")
rownames(w2pod)=mychem
rownames(w2podupper)=mychem
rownames(EC)=mychem
final.df=data.frame(direction,w2pod,w2podupper,EC)
final.notlog10.df=data.frame(direction,10^w2pod,10^w2podupper,10^EC)
names(final.auc)=seq(.25,3,by=.25)
write.csv(final.df,"TC_24hrs_Data_log10.csv")
write.csv(final.notlog10.df,"TC_24hrs_Data_originalscale.csv")


