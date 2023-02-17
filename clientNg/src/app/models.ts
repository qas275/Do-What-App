export interface userAllDetails{
    email:string
    favorites:TIHLocation[]
}

export interface TIHResponse{
    status:TIHResponseStatusCode
    data:TIHLocation[]
    totalRecords:number
    retrievedRecords:number
}

export interface TIHLocation{
    uuid:string
    name:string
    location:TIHgeoCode
    description:string
    address:TIHaddress
    images:TIHImageDetails[]
    dataset:string
    reviews:TIHreview[]
    contact:TIHcontact
    nearestMrtStation:string
    officialWebsite:string
    officialEmail:string
}

export interface TIHgeoCode{
    latitude:number
    longitude:number
}

export interface TIHaddress{
    block:number
    streetName:string
    floorNumber:number
    unitNumber:number
    buildingName:string
    postalCode:number
}

export interface TIHImageDetails{
    uuid:string
    url:string
    libraryUuid:string
    primaryFileMediumUuid:string
}

export interface TIHreview{
    authorName:string
    authorURL:string
    language:string
    profilePhoto:string
    rating:number
    text:string
    time:string
}

export interface TIHcontact{
    primaryContactNo:string
}

export interface TIHResponseStatusCode{
    code: number
    name:string
    message:string
}