Rails.application.routes.draw do
  get 'welcome/index'
  
  #resources :bankomat
  post '/bankomat/new', to: 'bankomat#create', as: 'bankomat_create'
  post '/bankomat/:id/edit/', to: 'bankomat#update', as: 'update_bankomat'
  post '/bankomat/:id/edit', to: 'bankomat#update', as: 'plus'
  resources :bankomat
  resolve('Bankomat') { [:deposit] }
  
  root 'welcome#index'
end
