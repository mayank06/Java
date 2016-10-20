package com.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackageProblem{

	public static void parserpackages(String[] sub2,List<Package> packages,double maxWeight){
	    for(int i=0;i<sub2.length;i++)
	    {
	    	String[] sub3=(sub2[i].substring(1,sub2[i].length()-1)).split(",");
	    	int id=Integer.parseInt(sub3[0]);
	    	double weight=Double.parseDouble(sub3[1]);
	    	double cost=Double.parseDouble(sub3[2].substring(1,sub3[2].length()));
	    	if(weight<=maxWeight)
	    	{
		        Package condidat=new Package(id,weight,cost);
		        packages.add(condidat);
	    	}
        }
	}
	static String getOptimumFor(List<Package> packages, int r,int NmaxWeight){
    	int indexSolution=0;
    	String returnData ="";
    	double maxWeight=0;
    	double maxCost=0;
	    int[] data=new int[r];
	    List<Integer> res = new ArrayList<Integer>();
	    int[] arr=new int[packages.size()];
	    for(int i=0;i<packages.size();i++)
	    {
	    	arr[i]=i;
	    }
	    getCombination(arr, data, res, 0, 0);
	    //res contien les combinaison de longeur r
	    //calcule des somme cost et weight
	    for(int i=0;i<=res.size()-r;i+=r)
	    {
	    	double somWeight=0;
	    	double somCost=0;
	    	for(int j=0;j<r;j++)
	    	{
	    		somWeight+=packages.get(res.get(i+j)).getWeight();
	    		somCost+=packages.get(res.get(i+j)).getCost();
	    	}
	    	if(somWeight<=NmaxWeight){
		    	if((somCost>maxCost)||((somCost==maxCost)&&(somWeight<=maxWeight)))
		    	{
		    		indexSolution=i;
		    		maxWeight=somWeight;
		    		maxCost=somCost;
		    	}
	    	}
	    }
    	for(int k=indexSolution;k<r+indexSolution;k++)
    	{
    		returnData+=res.get(k)+",";
    	}
	    return returnData+maxCost+","+maxWeight;
	}
	static void getCombination(int arr[], int data[],List<Integer> res, int start, int index)
	{
	    if (index == data.length)
	    {
	        for (int j=0; j<data.length; j++){
	        	res.add(data[j]);
	        }
	        return;
	    }
	    for (int i=start; i<arr.length && arr.length-i >= data.length-index; i++)
	    {
	        data[index] = arr[i];
	        getCombination(arr, data,res, i+1, index+1);
	    }
	}
    public static void main (String[] args) {
    File file = new File("D:/packageproblem.txt");
    BufferedReader in = null;
	try {
		in = new BufferedReader(new FileReader(file));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
    String line;
    List<Package> packages=new ArrayList<Package>();
    try {
		while ((line = in.readLine()) != null) {
				String s="";
				//Parsing line
		        String[] sub1=line.split(" : ");
		        int N=Integer.parseInt(sub1[0]);
		        String[] sub2=sub1[1].split(" ");
		        if(sub2.length>1)
		        {
		        packages.clear();
		        parserpackages(sub2,packages,N);

			    double maxCost=0;
			    double maxWeight=0;
			    for(int i=1;i<=packages.size();i++)
			    {
			    	String resultat=getOptimumFor(packages,i,N);
			    	//System.out.println(resultat);
			    	String[] sub4=resultat.split(",");
			    	double cost=Double.parseDouble(sub4[sub4.length-2]);
			    	double weight=Double.parseDouble(sub4[sub4.length-1]);
			    	if(cost==maxCost)
			    	{
			    		if(weight<maxWeight){
				    		maxCost=cost;
				    		maxWeight=weight;
			    			s=resultat;
			    		}
			    	}
			    	if(cost>maxCost)
			    	{
			    		maxCost=cost;
			    		maxWeight=weight;
			    		s=resultat;
			    	}
			    }
			    //System.out.println(s);
			    String[] sub5=s.split(",");
			    String ss="";
			    for(int i=0;i<sub5.length-2;i++)
			    {
			    	ss+=packages.get(Integer.parseInt(sub5[i])).getId()+",";
			    }
			    if(ss.equals(""))
			    	System.out.println("-");
			    else
			    	System.out.println(ss.substring(0,ss.length()-1));
		        }else
		        	System.out.println("-");

		}

	} catch (IOException e) {
		e.printStackTrace();
	}
    }
}
class Package {

	private int id;
	private double weight;
	private double cost;

	public Package() {
		super();
	}
	public Package(int id, double weight, double cost) {
		super();
		this.id = id;
		this.weight = weight;
		this.cost = cost;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "Package [id=" + id + ", weight=" + weight + ", cost=" + cost
				+ "]";
	}

}
