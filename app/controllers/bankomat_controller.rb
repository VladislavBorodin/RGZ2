class BankomatController < ApplicationController
def index
    @bankomats = Bankomat.all
end
 def show
    @bankomat = Bankomat.find(params[:id])
 end
def new
   @bankomat = Bankomat.new
  end
def edit
  @bankomat = Bankomat.find(params[:id])
end
 def create
  @bankomat = Bankomat.new(bankomat_params)
  if @bankomat.save
    redirect_to bankomat_index_url
  else
    render 'new'
  end
end
def update
@bankomat = Bankomat.find(params[:id])
@bankomat.balance+=params[:rating].to_i
@bankomat.save
redirect_to edit_bankomat_url

end
def destroy
    @bankomat = Bankomat.find(params[:id])
    @bankomat.destroy
 
    redirect_to bankomat_index_url
  end
def plus 
@bankomat = Bankomat.find(params[:id])
@bankomat.balance+=params[:deposit].to_i
#@bankomat.balance+=100
@bankomat.save
end
  
private
  def bankomat_params
    params.permit(:name, :password, :number,:balance)
  end
end
